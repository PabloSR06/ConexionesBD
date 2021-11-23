<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editando</title>
    <meta http-equiv="refresh" content="10;url=Consultas.php">
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="header">
        <header class="nav_bar">
            <nav class="nav_link_nav ">
            <!--Btn from the navbar-->
                <ul class="nav_link_ul">
                    <li class="nav_link_li">
                        <a class="nav_link " href="Insertar.php">Insertar</a>
                    </li>

                    <li class="nav_link_li">
                        <a class="nav_link" href="Consultas.php">Ver/Borrar</a>
                    </li>

                    <li class="nav_link_li">
                        <a class="nav_link" href="Functions.php">Funciones</a>
                    </li>
                </ul> 

            </nav>
        </header>
    </div>
    <h1>PABLO SUAREZ ROMERO</h1>    

    <?php
    require 'conectaDB.php'; 
    $pdo = conectaDB();

    echo "<h2 style='padding-top: 50px;'>Actualizar</h2>";
    $codHotel=$_POST['codHotel'];
    $numHabitacion=$_POST['numHabitacion'];
    $capacidad=$_POST['capacidad'];
    $preciodia=$_POST['preciodia'];
    //$activa=$_POST['activa'];
    
    if(filter_has_var(INPUT_POST,'activa')) {
        $activa = 1;
    }else{
        $activa = 0;
    }  

    try {
        $stm = $pdo->prepare(
            "UPDATE habitaciones SET  
            numHabitacion = :num, 
            capacidad =:capacidad,
            preciodia = :preciodia,
            activa = :activa 
            WHERE codHotel = :codhotel AND numHabitacion = :num");
        
        $stm -> bindParam(':codhotel', $codHotel, PDO::PARAM_STR, 6);
        $stm -> bindParam(':num', $numHabitacion, PDO::PARAM_STR, 4);
        $stm -> bindParam(':capacidad', $capacidad, PDO::PARAM_INT);
        $stm -> bindParam(':preciodia', $preciodia, PDO::PARAM_INT);
        $stm -> bindParam(':activa', $activa, PDO::PARAM_INT);
/*
        $stm -> bindValue(':codhotel', $codHotel);
        $stm -> bindValue(':num', $numHabitacion, );
        $stm -> bindValue(':capacidad', $capacidad,);
        $stm -> bindValue(':preciodia', $preciodia,);
        $stm -> bindValue(':activa', $activa,);*/
        
        $stm -> execute();   


        echo "<table class='table_consulta'>";
        echo "<tr>";
        echo "<th>CodHotel</th>";
        echo "<th>numHabitacion</th>";
        echo "<th>Capacidad</th>";
        echo "<th>preciodia</th>";
        echo "<th>activa</th>";
        echo "</tr>";


        echo "<tr>";

        echo "<td>" .$codHotel ."</td>";
        echo "<td>" .$numHabitacion."</td>";
        echo "<td>" .$capacidad."</td>";
        echo "<td>" .$preciodia."</td>";
        echo "<td>" .$activa."</td>";
        echo "</tr>";
        echo "</table>";      
     
    } catch (\Throwable $th) {
        echo  "<br>" . $th->getMessage();  
        echo "no se puede" ;
    }

    ?>

    <p>Redireccionando</p>

</body>
</html>