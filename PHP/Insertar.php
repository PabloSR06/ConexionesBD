<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertar</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <div class="header">
        <header class="nav_bar">
            <nav class="nav_link_nav ">
            <!--Btn from the navbar-->
                <ul class="nav_link_ul">
                    <li class="nav_link_li">
                        <a class="nav_link active" href="#">Insertar</a>
                    </li>

                    <li class="nav_link_li">
                        <a class="nav_link" href="Consultas.php">Ver/Borrar</a>
                    </li>

                    <li class="nav_link_li">
                        <a class="nav_link" href="Functions.php">Modificar</a>
                    </li>
                </ul> 

            </nav>
        </header>
    </div>

<?php
    require 'conectaDB.php'; 
    $db = conectaDB();
?>


    <h1>Pablo Suarez Romero</h1>
    <div class="div_insert">

    <form method="POST" action="add.php">
    
    <label class="labelname" for="">CodHotel</label>
    
            <select class="entrada" name="codH">

                <?php
                    $query = $db->prepare("SELECT * FROM hoteles");
                    $query->execute();
                    $data = $query->fetchAll();
        
                    foreach ($data as $valores):
                        echo '<option value="'.$valores["codHotel"].'">'.$valores["codHotel"]." + ".$valores["NomHotel"].'</option>';
                    endforeach;
        
                ?>
            </select>

        <label class="labelname"for="">numHabitacion</label>
        <input class="entrada" type="number" name="num">

        <label class="labelname"for="">Capacidad</label>
        <input class="entrada" type="number" name="capacidad">

        <label class="labelname"for="">preciodia</label>
        <input class="entrada" type="number" name="preciodia">
    
        <label class="labelname"for="">activa</label>
        <input class="entrada" type="checkbox" name="activa">         
        
        <input class="entrada" class="btn" type="submit">
    </form>
    </div>

    
</body>
</html>