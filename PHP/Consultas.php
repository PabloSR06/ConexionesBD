<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Consultas/Borrar</title>

</head>
<body>
    <?php
        require 'conectaDB.php'; 
        $db = conectaDB();

    ?>


    <div class="header">
        <header class="nav_bar">
            <nav class="nav_link_nav ">
            <!--Btn from the navbar-->
                <ul class="nav_link_ul">
                    <li class="nav_link_li">
                        <a class="nav_link " href="Insertar.php">Insertar</a>
                    </li>

                    <li class="nav_link_li">
                        <a class="nav_link active" href="Consultas.php">Ver/Borrar</a>
                    </li>

                    <li class="nav_link_li">
                        <a class="nav_link" href="Functions.php">Funciones</a>
                    </li>
                </ul> 

            </nav>
        </header>
    </div>

    <h1>PABLO SUAREZ ROMERO</h1>
    <h2>Consultas</h2>
    <?php
    if(isset($_POST['eliminar'])){

        try {
            $consulta = "DELETE FROM habitaciones where codHotel = :codhotel AND numHabitacion = :habitacion";
            $sql = $db-> prepare($consulta);
            $sql -> bindParam(':codhotel', $codHotel, PDO::PARAM_INT);
            $sql -> bindParam(':habitacion', $habitacion, PDO::PARAM_INT);
            $codHotel = $_POST['codHotel'];
            $habitacion = $_POST['numHabitacion'];
            $sql->execute();
        } catch (\Throwable $th) {
            echo "<div> No se pudo eliminar, la cable esta como clave foranea en otra tabla</div>";
        }

    }
    ?>
    
    <?php 
        $stmt = $db->query('SELECT*from habitaciones INNER JOIN hoteles ON habitaciones.codHotel= hoteles.codHotel');
        

        echo "<table class='table_consulta'>";
        echo "<tr>";
        echo "<th>CodHotel</th>";
        echo "<th>NomHotel</th>";
        echo "<th>numHabitacion</th>";
        echo "<th>Capacidad</th>";
        echo "<th>preciodia</th>";
        echo "<th>activa</th>";
        echo "<th></th>";
        echo "<th></th>";
        echo "</tr>";


        while($row = $stmt->fetch()) {
            echo "<tr>";

            echo "<td>" .$row['codHotel'] ."</td>";
            echo "<td>" .$row['NomHotel'] ."</td>";
            echo "<td>" .$row['numHabitacion']."</td>";
            echo "<td>" .$row['capacidad']."</td>";
            echo "<td>" .$row['preciodia']."</td>";
            echo "<td>" .$row['activa'] ."</td>";
            echo "<td>
            <form  onsubmit=\"return confirm('Realmente desea eliminar el registro?');\" method='POST' action='".$_SERVER['PHP_SELF']."'>
            <input type='hidden' name='codHotel' value='".$row['codHotel']."'>
            <input type='hidden' name='numHabitacion' value='".$row['numHabitacion']."'>
            <button name='eliminar'>Eliminar</button>
            </form>
            </td>";
            echo "<td>
            <form  method='POST' action='".$_SERVER['PHP_SELF']."'>
            <input type='hidden' name='codHotel' value='".$row['codHotel']."'>
            <input type='hidden' name='numHabitacion' value='".$row['numHabitacion']."'>
            <input type='hidden' name='capacidad' value='".$row['capacidad']."'>
            <input type='hidden' name='preciodia' value='".$row['preciodia']."'>
            <input type='hidden' name='activa' value='".$row['activa']."'>
            <button name='editar'>Editar</button>
            </form>
            </td>";
            echo "</tr>";

        }
        echo "</table>";        
    ?>

<?php
    if(isset($_POST['editar'])){
        try {
            echo "<h2 style='padding-top: 50px;'>Actualizar</h2>";
            $codHotel=$_POST['codHotel'];
            $numHabitacion=$_POST['numHabitacion'];
            $capacidad=$_POST['capacidad'];
            $preciodia=$_POST['preciodia'];
            $activa=$_POST['activa'];
            $on;
            if($activa == 1){
                $on = 'checked';
            }else{
                $on = 'off';
            }

            echo "<div class='div_insert'>";
            echo "<form method='POST' action='edit.php'>";
            
            echo "<label class='labelname'>CodHotel</label>";
            echo "<input class='entrada' type='number' name='codHotel' value=".$codHotel.">";

            echo "<label class='labelname'>numHabitacion</label>";
            echo "<input class='entrada' type='number' name='numHabitacion' value=".$numHabitacion.">";
        
            echo "<label class='labelname'>Capacidad</label>";
            echo "<input class='entrada' type='number' name='capacidad' value=".$capacidad.">";
        
            echo "<label class='labelname'>preciodia</label>";
            echo "<input class='entrada' type='number' name='preciodia' value=".$preciodia.">";
        
            echo "<label class='labelname'>activa</label>";
            echo "<input class='entrada' type='checkbox' name='activa' $on>";

            echo "<input class='entrada' class='btn' type='submit'>";  
            echo "</form>"; 
            echo "<form  method='POST' action='".$_SERVER['PHP_SELF']."'>
            <button class='entrada' class='btn' name='cancelar'>Cancelar</button>
            </form>";
            echo "</div>";   
            
    
     } catch (\Throwable $th) {
            echo "<div>No se puede editar</div>";

        }

    }
    ?>

   

</body>
</html>