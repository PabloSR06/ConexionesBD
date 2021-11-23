/*PABLO SUAREZ ROMERO*/
CREATE DATABASE bdhoteles1;
USE bdhoteles1;


CREATE TABLE  hoteles(
    codHotel CHAR(6) NOT NULL,
    CONSTRAINT PK_CODHOTEL PRIMARY KEY (codHotel),
    NomHotel Varchar(60)
    );

CREATE TABLE habitaciones(
    codHotel CHAR(6) NOT NULL, 
    numHabitacion VARCHAR(4) NOT NULL,    
    CONSTRAINT PK_NUMHABITACION PRIMARY KEY ( numHabitacion),
    CONSTRAINT FK_CODHOTEL FOREIGN KEY (codHotel) REFERENCES hoteles(codHotel),
    capacidad INT DEFAULT 2,
    preciodia INT NOT NULL,
    activa BOOLEAN DEFAULT 1
);
CREATE TABLE regimenes(
    codregimen INT NOT NULL AUTO_INCREMENT,
    CONSTRAINT PK_CODREGIMEN PRIMARY KEY (codregimen),
    codHotel CHAR(6) NOT NULL, 
    CONSTRAINT FK_CODHOTEL2 FOREIGN KEY (codHotel) REFERENCES hoteles(codHotel),
    tipo CHAR(2) CHECK (tipo = 'DY' OR tipo='MD' OR tipo='MD' OR tipo='TD'),
    precio INT NOT NULL
);



CREATE TABLE clientes(
    coddnionie CHAR(9) NOT NULL,
    CONSTRAINT PK_CODDNI PRIMARY KEY (coddnionie),
    nombre VARCHAR(50) NOT NULL,
    nacionalidad VARCHAR(40) NOT NULL
);

CREATE TABLE estancias(
    codestancia INT NOT NULL AUTO_INCREMENT, 
    CONSTRAINT PK_CODESTANCIA PRIMARY KEY (codestancia),
    coddnionie CHAR(9) NOT NULL,
    CONSTRAINT FK_CODDNI FOREIGN KEY (coddnionie) REFERENCES clientes(coddnionie),
    codHotel CHAR(6) NOT NULL, 
    CONSTRAINT FK_CODHOTEL3 FOREIGN KEY (codHotel) REFERENCES hoteles(codHotel),
    numHabitacion VARCHAR(4) NOT NULL,
    CONSTRAINT FK_HABITACIONES FOREIGN KEY (numHabitacion) REFERENCES habitaciones(numHabitacion),
    fechaInicio Date NOT NULL,
    fechaFin DATE NOT NULL,
    codregimen INT NOT NULL, 
    CONSTRAINT FK_regimen FOREIGN KEY (codregimen) REFERENCES regimenes(codregimen),
    ocupantes INT DEFAULT 2,
    precioestancia INT NOT NULL,
    pagado BOOLEAN DEFAULT 1
);


INSERT INTO hoteles VALUES 
(001, 'hotel1'),
(002, 'hotel2'),
(003, 'hotel3'),
(004, 'hotel4'),
(005, 'hotel5');

INSERT INTO clientes VALUES
('00000001A', 'cliente1', 'nacion1'),
('00000002A', 'cliente2', 'nacion1'),
('00000003A', 'cliente3', 'nacion2'),
('00000004A', 'cliente4', 'nacion3'),
('00000005A', 'cliente5', 'nacion2');

INSERT INTO regimenes VALUES
(100, 002, 'DY', 300 ),
(101, 002, 'MD', 323 ),
(102, 003, 'DY', 234 ),
(103, 005, 'TD', 421 ),
(104, 003, 'TD', 500 );

INSERT INTO habitaciones VALUES
(002, 25, 2, 12, 0),
(002, 1, 2, 30, 1),
(002, 23, 2, 34, 1),
(003, 43, 3, 43, 0),
(005, 24, 4, 65, 1),
(005, 12, 2, 34, 0);

INSERT INTO estancias VALUES
(002, '00000003A', 002, 1, '2021/09/02', '2021/09/10', 100, 2, 300, 1),
(003, '00000003A', 002, 1, '2021/09/01', '2021/09/14', 100, 2, 243, 1);


DELIMITER //

CREATE PROCEDURE EJ_01 (nom VARCHAR(60) )
BEGIN
    

    SELECT numHabitacion, capacidad, preciodia, activa FROM habitaciones 
    INNER JOIN hoteles ON
    habitaciones.codHotel = hoteles.codHotel
    WHERE nomHotel = nom
    ORDER BY preciodia, capacidad ASC;

END;

// DELIMITER;

/*
CALL EJ_01('hotel2');
*/

DELIMITER //
CREATE PROCEDURE EJ_02 (in hotel char(6), in numHabitacion varchar(4), in capacidad tinyint, in preciodia int, in activa bit, out salida1 int, out salida2 int)
BEGIN	
    if ((select count(*) from hoteles where codHotel=hotel) != 0) THEN
            insert into habitaciones values (hotel, numHabitacion, capacidad, preciodia, activa);
            set salida1=1;
            set salida2=1;
    else 
            set salida1=0;
            set salida2=0;
    end IF;
END
 
// 
DELIMITER ;
/*
si existe

call EJ_02(123456, 50, 10, 400, 1, @salida1 ,@salida2);
select @salida1, @salida2;

no existe

all EJ_02(126456, 100, 150, 250, 1, @salida1 ,@salida2);
select @salida1, @salida2;

*/




DELIMITER //


CREATE PROCEDURE EJ_03 (IN NOM_HOTEL VARCHAR(60), IN PRECIO_DIA INT, OUT CANT_HAB_TOTAL int, OUT CANT_HAB_PRECIO int)

BEGIN
    SET CANT_HAB_TOTAL = (SELECT count(NUMHABITACION) FROM habitaciones
        INNER JOIN hoteles ON hoteles.codHotel = habitaciones.codHotel 
        WHERE NOM_HOTEL = hoteles.NomHotel);
    SET CANT_HAB_PRECIO = (SELECT count(NUMHABITACION) FROM habitaciones 
        INNER JOIN hoteles ON hoteles.codHotel = habitaciones.codHotel 
        WHERE NOM_HOTEL = hoteles.NomHotel 
        AND habitaciones.PRECIODIA < PRECIO_DIA AND ACTIVA = 0);
END



// DELIMITER;


/*CALL EJ_03 ('hotel1', 100, @CANT_HAB_TOTAL, @CANT_HAB_PRECIO);
SELECT @CANT_HAB_TOTAL;
SELECT @CANT_HAB_PRECIO;*/


DELIMITER //

CREATE FUNCTION EJ_04 (dni CHAR(9))
RETURNS INT
BEGIN
    DECLARE SALIDA INT;

    SELECT SUM(precioestancia) INTO SALIDA FROM estancias 
    WHERE coddnionie = dni;
    
    RETURN SALIDA;
END

// DELIMITER;

/*
SELECT EJ_04('00000003A');
*/
DELIMITER //

create trigger EJ_01 
before insert on estancias
for each ROW
BEGIN
	declare capacidad2 int;
    set capacidad2=(select capacidad from habitaciones WHERE new.codHotel=habitaciones.codHotel and 				new.numHabitacion=habitaciones.numHabitacion);
	if(NEW.ocupantes>capacidad2) then	
        	signal sqlstate '45000' set message_text='no se pudo insertar';
    END if;
END

//
DELIMITER ;



DELIMITER //

CREATE TRIGGER EJ_02 
BEFORE INSERT on estancias
FOR EACH ROW
BEGIN
DECLARE PRECIO int;
    SET PRECIO= (SELECT DATEDIFF(NEW.fechaFin, NEW.fechaInicio)*
        ((SELECT preciodia FROM habitaciones 
        WHERE NEW.codHotel=codHotel 
        AND NEW.numHabitacion=habitaciones.numHabitacion)+
        (SELECT precio FROM regimenes WHERE NEW.codregimen=regimenes.codregimen)
        *(NEW.ocupantes)));
    SET new.precioestancia=PRECIO;
END
// DELIMITER;


/*
INSERT INTO estancias VALUES
(004, '00000004A', 003, 1, '2021/09/02', '2021/09/10', 103, 2, 0, 0),
(004, '00000005A', 003, 1, '2021/09/02', '2021/09/10', 120, 2, 0, 0);
*/

DELIMITER //

create trigger EJ_TR_03 
before insert on estancias
for each ROW
BEGIN
	
    signal sqlstate '45000' set message_text='no se pudo insertar';
END

//
DELIMITER ;