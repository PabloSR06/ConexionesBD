CREATE DATABASE bdHoteles;
GO

USE bdHoteles;
GO
CREATE TABLE  hoteles(
    codHotel CHAR(6) NOT NULL,
    CONSTRAINT PK_CODHOTEL PRIMARY KEY (codHotel),
    NomHotel Varchar(60)
    );
	GO

CREATE TABLE habitaciones(
    codHotel CHAR(6) NOT NULL, 
    CONSTRAINT FK_CODHOTEL FOREIGN KEY (codHotel) REFERENCES hoteles(codHotel),
    numHabitacion VARCHAR(4) NOT NULL,    
    CONSTRAINT PK_NUMHABITACION PRIMARY KEY (numHabitacion),
    capacidad INT DEFAULT 2,
    preciodia INT NOT NULL,
    activa BIT DEFAULT 1
);

GO

CREATE TABLE regimenes(
    codregimen INT NOT NULL IDENTITY(1,1),
    CONSTRAINT PK_CODREGIMEN PRIMARY KEY (codregimen),
    codHotel CHAR(6) NOT NULL, 
    CONSTRAINT FK_CODHOTEL2 FOREIGN KEY (codHotel) REFERENCES hoteles(codHotel),
    tipo CHAR(2) CHECK (tipo = 'DY' OR tipo='MD' OR tipo='MD' OR tipo='TD'),
    precio INT NOT NULL
);

GO



CREATE TABLE clientes(
    coddnionie CHAR(9) NOT NULL,
    CONSTRAINT PK_CODDNI PRIMARY KEY (coddnionie),
    nombre VARCHAR(50) NOT NULL,
    nacionalidad VARCHAR(40) NOT NULL
);
GO


CREATE TABLE estancias(
    codestancia INT NOT NULL IDENTITY(1,1), 
    CONSTRAINT PK_CODESTANCIA PRIMARY KEY (codestancia),
    coddnionie CHAR(9) NOT NULL,
    codHotel CHAR(6) NOT NULL, 
    
    numHabitacion VARCHAR(4) NOT NULL,

    fechaInicio Date NOT NULL,
    fechaFin DATE NOT NULL,
    codregimen INT NOT NULL, 
    ocupantes INT DEFAULT 2,
    precioestancia INT NOT NULL,
    pagado BIT DEFAULT 1
);

GO


ALTER TABLE  estancias add CONSTRAINT FK_regimen FOREIGN KEY (codregimen) REFERENCES regimenes(codregimen);
ALTER TABLE  estancias add CONSTRAINT FK_HABITACIONES FOREIGN KEY (numHabitacion) REFERENCES habitaciones(numHabitacion);
ALTER TABLE  estancias add CONSTRAINT FK_CODHOTEL3 FOREIGN KEY (codHotel) REFERENCES hoteles(codHotel);
ALTER TABLE  estancias add CONSTRAINT FK_CODDNI FOREIGN KEY (coddnionie) REFERENCES clientes(coddnionie);

GO
INSERT hoteles VALUES 
(001, 'hotel1'),
(002, 'hotel2'),
(003, 'hotel3'),
(004, 'hotel4'),
(005, 'hotel5');
GO


INSERT clientes VALUES
('00000001A', 'cliente1', 'nacion1'),
('00000002A', 'cliente2', 'nacion1'),
('00000003A', 'cliente3', 'nacion2'),
('00000004A', 'cliente4', 'nacion3'),
('00000005A', 'cliente5', 'nacion2');
GO


INSERT regimenes (codHotel, tipo, precio) VALUES
(002, 'DY', 300 ),
(002, 'MD', 323 ),
(003, 'DY', 234 ),
(005, 'TD', 421 ),
(003, 'TD', 500 );
GO



INSERT habitaciones VALUES
(002, 25, 2, 12, 0),
(002, 1, 2, 30, 1),
(002, 23, 2, 34, 1),
(003, 43, 3, 43, 0),
(005, 24, 4, 65, 1),
(005, 12, 2, 34, 0);
GO

INSERT estancias ( coddnionie, codHotel, numHabitacion, fechaInicio, fechaFin, codregimen, ocupantes, precioestancia, pagado)VALUES
('00000003A', 002, 1, '2021/09/02', '2021/09/10', 1, 2, 300, 1),
( '00000003A', 002, 1, '2021/09/01', '2021/09/14', 1, 2, 243, 1);
GO





CREATE PROCEDURE EJ_01 
@nom VARCHAR(60)
AS
    SELECT numHabitacion, capacidad, preciodia, activa FROM habitaciones 
    INNER JOIN hoteles ON
    habitaciones.codHotel = hoteles.codHotel
    WHERE nomHotel = @nom
    ORDER BY preciodia, capacidad ASC;

GO

/*
EXEC EJ_01  @nom = 'hotel2';
*/


CREATE PROCEDURE EJ_02 
@hotel char(6), 
@numHabitacion varchar(4), 
@capacidad tinyint, 
@preciodia int, 
@activa bit,
@salida1 int output,
@salida2 int output

AS	
    if ((select count(*) from hoteles where codHotel=@hotel) != 0) 
		BEGIN
            insert into habitaciones values (@hotel, @numHabitacion, @capacidad, @preciodia, @activa);
            set @salida1=1;
            set @salida2=1;
		END
    ELSE 
		BEGIN
            set @salida1=0;
            set @salida2=0;
		END

GO


go
create PROCEDURE EJ_03
@hotel varchar(60),
@precio int,
@cantidadHotel int output,
@cantidadPrecio int output
as
set @cantidadHotel= (select COUNT(numHabitacion) from habitaciones 
INNER join hoteles on hoteles.codHotel=habitaciones.codHotel where @hotel=hoteles.nomHotel);
set @cantidadPrecio= (select COUNT(numHabitacion) from habitaciones 
INNER join hoteles on hoteles.codHotel=habitaciones.codHotel where @hotel=hoteles.nomHotel AND @precio<preciodia and activa=0);
go

 
CREATE FUNCTION EJ_04
(@dni CHAR(9))
RETURNS INT
AS
	BEGIN
		DECLARE @SALIDA  INT

		SET @SALIDA = (SELECT SUM(precioestancia) FROM estancias 
		WHERE coddnionie = @dni);
    
		RETURN @SALIDA;
	END
GO


/*
SELECT DBO.EJ_04('00000003A');
*/



create trigger EJ_tr_01 on estancias for insert
AS
declare @capacidad2 int
declare @ocupantes int
BEGIN

    set @capacidad2=(select capacidad from habitaciones INNER JOIN inserted on
	inserted.codHotel = habitaciones.codHotel and
	inserted.numHabitacion = habitaciones.numHabitacion);
	
	set @ocupantes = (select ocupantes from inserted);


	if(@ocupantes>@capacidad2)	
        BEGIN
        	RAISERROR(
			'No se pudo', 16, 1)
			ROLLBACK TRANSACTION
		END
END
GO




CREATE TRIGGER EJ_TR_02 on estancias instead of insert
AS
declare @numerodias int;
declare @preciodiahabitacion int;
declare @ocupantes int;
declare @precioregimen int;
declare @precioestancia int;
BEGIN

    set @numerodias= (select datediff(day,fechaInicio,fechaFin) from inserted);
    set @preciodiahabitacion= (select preciodia from inserted inner join habitaciones on habitaciones.codHotel=inserted.codHotel 
    where inserted.codHotel=habitaciones.codHotel and inserted.numHabitacion=habitaciones.numHabitacion);
    set @ocupantes= (select ocupantes from inserted)
    set @precioregimen= (select precio from inserted inner join regimenes on regimenes.codregimen=inserted.codregimen 
    where inserted.codregimen=regimenes.codregimen);
        
    set @precioestancia=(@numerodias*(@preciodiahabitacion+(@precioregimen*@ocupantes)));
END
GO


CREATE TRIGGER EJ_TR_03 ON hoteles for DELETE
AS
BEGIN
    RAISERROR(
			'No se pudo', 16, 1)
			ROLLBACK TRANSACTION
END
GO



