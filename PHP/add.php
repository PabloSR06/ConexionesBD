<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Añadiendo</title>
    <meta http-equiv="refresh" content="10;url=Insertar.php">
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

    

    if(filter_has_var(INPUT_POST,'activa')) {
        $activa = 1;
    }else{
        $activa = 0;
    }    
    
    $codhotel = $_POST['codH'];
    $num = $_POST['num'];
    $capacidad = $_POST['capacidad'];
    $preciodia = $_POST['preciodia'];


    try {
        $stm = $pdo->prepare(
            "INSERT INTO habitaciones (codHotel, numHabitacion, capacidad, preciodia,activa) 
            VALUES (:codhotel, :num, :capacidad,:preciodia,:activa)");
        $stm -> bindValue(':codhotel', $codhotel);
        $stm -> bindValue(':num', $num);
        $stm -> bindValue(':capacidad', $capacidad);
        $stm -> bindValue(':preciodia', $preciodia);
        $stm -> bindValue(':activa', $activa);
        
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

        echo "<td>" .$codhotel ."</td>";
        echo "<td>" .$num ."</td>";
        echo "<td>" .$capacidad."</td>";
        echo "<td>" .$preciodia."</td>";
        echo "<td>" .$activa."</td>";
        echo "</tr>";
        echo "</table>";      
     
    } catch (\Throwable $th) {
        //echo $stm . "<br>" . $e->getMessage();  
        echo "no se pudo insertar" ;
    }

    ?>

    <p>Redireccionando</p>

</body>
</html>