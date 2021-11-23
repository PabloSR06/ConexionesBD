<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Functions</title>

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
                        <a class="nav_link" href="Consultas.php">Ver/Borrar</a>
                    </li>

                    <li class="nav_link_li">
                        <a class="nav_link active" href="#">Funciones</a>
                    </li>
                </ul> 

            </nav>
        </header>
    </div>

    <h1>PABLO SUAREZ ROMERO</h1>
    <h2>Funciones y procedimientos</h2>
    <!--PROCEDIMIENTO 1-->
    <div>
        <h3>Procedimiento 1</h3>

        <div class="div_insert">
            <?php
                echo "
                <form  method='post' action='".$_SERVER['PHP_SELF']."'>
                    <label class='labelname'>nomHotel</label>
                    <select class='entrada' name='nomHotel'>";

                
                    $query = $db->prepare("SELECT * FROM hoteles");
                    $query->execute();
                    $data = $query->fetchAll();
        
                    foreach ($data as $valores):
                        echo '<option value="'.$valores["NomHotel"].'">'.$valores["NomHotel"].'</option>';
                    endforeach;
        
                 echo "
                    </select>
                    <button class='btn' name='FNC_01'>Enviar</button>
                </form>";
            ?>
        </div>
        <?php
            if(isset($_POST['FNC_01'])){
                $nomHotel=$_POST['nomHotel'];
                try {
                    $sql = $db-> prepare("Call EJ_01(:nomHotel)");
                    $sql -> bindParam(':nomHotel', $nomHotel, PDO::PARAM_STR);
                    $sql->execute();

                    echo "<table class='table_consulta'>";
                    echo "<tr>";
                    echo "<th>numHabitacion</th>";
                    echo "<th>Capacidad</th>";
                    echo "<th>preciodia</th>";
                    echo "<th>activa</th>";
                    echo "</tr>";

                    while($row = $sql->fetch()) {
                        echo "<tr>";
                        echo "<td>" .$row['numHabitacion'] ."</td>";
                        echo "<td>" .$row['capacidad'] ."</td>";
                        echo "<td>" .$row['preciodia']."</td>";
                        echo "<td>" .$row['activa']."</td>";
                        echo "</tr>";
                    }
                    echo "</table>";
                    
                } catch (\Throwable $th) {
                    echo "<div>ERROR</div>";
                }
            }
        ?>
    </div>
    <!--PROCEDIMIENTO 2-->
    <div>
        <h3>Procedimiento 2</h3>
        <div class="div_insert">
            <?php
                echo "
                <form  method='post' action='".$_SERVER['PHP_SELF']."'>
                    <label class='labelname'>CodHotel</label>
                    <input type='number' class='entrada' name='codHotel'>

                    <label class='labelname'for=''>numHabitacion</label>
                    <input class='entrada' type='number' name='numHabitacion'>
            
                    <label class='labelname'for=''>Capacidad</label>
                    <input class='entrada' type='number' name='capacidad'>
            
                    <label class='labelname'for=''>preciodia</label>
                    <input class='entrada' type='number' name='preciodia'>
                
                    <label class='labelname'for=''>activa</label>
                    <input class='entrada' type='checkbox' name='activa'> 


                    <button class='btn' name='FNC_02'>Enviar</button>
                </form>";
            ?>
        </div>
        <?php
            if(isset($_POST['FNC_02'])){
                
                if(filter_has_var(INPUT_POST,'activa')) {
                    $activa = 1;
                }else{
                    $activa = 0;
                }    
                
                $codHotel = $_POST['codHotel'];
                $numHabitacion = $_POST['numHabitacion'];
                $capacidad = $_POST['capacidad'];
                $preciodia = $_POST['preciodia'];

                try {
                    $sql = $db-> prepare("Call EJ_02(:codHotel,:numHabitacion,:capacidad, :preciodia, :activa, @salida1, @salida2)");
                    $sql -> bindValue(':codHotel', $codHotel);
                    $sql -> bindValue(':numHabitacion', $numHabitacion);
                    $sql -> bindValue(':capacidad', $capacidad);
                    $sql -> bindValue(':preciodia', $preciodia);
                    $sql -> bindValue(':activa', $activa);
                    $sql->execute();

                    $sql =$db->query('select @salida1 as salida1'); 
                    $row = $sql->fetch(); 

                    $sql =$db->query('select @salida2 as salida2'); 
                    $row2 = $sql->fetch(); 

                    
                    echo "<table class='table_consulta'>";
                    echo "<tr>";
                    echo "<th>Salida 1 </th>";
                    echo "<th>Salida 2</th>";
                    echo "</tr>";
                    echo "<tr>";
                    echo "<td>
                    0 = hotel no existe <br>
                    1 = hotel existe
                     </td>";
                    echo "<td>
                    0 = habitacion no insertada <br>
                    1 = insercion correcta
                    </td>";
                    echo "</tr>";

                   
                    echo "<tr>";
                    echo "<td>" .$row['salida1'] ."</td>";
                    echo "<td>" .$row2['salida2'] ."</td>";
                    echo "</tr>";
                    
                    echo "</table>";
                    
                } catch (\Throwable $th) {
                    echo  $th->getMessage();  
                    echo "<div>ERROR</div>";
                }
                
            }
        ?>
    </div>

    <!--PROCEDIMIENTO 3-->
    <div>
        <h3>Procedimiento 3</h3>

        <div class="div_insert">
            <?php
                echo "
                <form  method='post' action='".$_SERVER['PHP_SELF']."'>
                    <label class='labelname'>nomHotel</label>
                    <select class='entrada' name='nomHotel'>";

                
                    $query = $db->prepare("SELECT * FROM hoteles");
                    $query->execute();
                    $data = $query->fetchAll();
        
                    foreach ($data as $valores):
                        echo '<option value="'.$valores["NomHotel"].'">'.$valores["NomHotel"].'</option>';
                    endforeach;
        
                 echo "
                    </select>
                    <label class='labelname'>Preciodia</label>  
                    <input type='number' class='entrada' name='preciodia'>
                    <button class='btn' name='FNC_03'>Enviar</button>
                </form>";
            ?>
        </div>
        <?php
            if(isset($_POST['FNC_03'])){
                $nomHotel=$_POST['nomHotel'];
                $preciodia=$_POST['preciodia'];
                try {
                    $sql = $db-> prepare("Call EJ_03(:nomHotel,:preciodia, @salida1, @salida2)");
                    $sql -> bindValue(':nomHotel', $nomHotel);
                    $sql -> bindValue(':preciodia', $preciodia);

                    $sql->execute();

                    $sql =$db->query('select @salida1 as salida1, @salida2 as salida2'); 
                    $row = $sql->fetch(); 
                    /*$sql =$db->query('select @salida1 as salida1'); 
                    $row = $sql->fetch(); 

                    $sql =$db->query('select @salida2 as salida2'); 
                    $row2 = $sql->fetch(); */


                    echo "<table class='table_consulta'>";
                    echo "<tr>";
                    echo "<th>Cantidad</th>";
                    echo "<th>Capacidad</th>";
                    echo "</tr>";

                   
                    echo "<tr>";
                    echo "<td>" .$row['salida1'] ."</td>";
                    echo "<td>" .$row['salida2'] ."</td>";
                    echo "</tr>";
                    
                    echo "</table>";
                    
                } catch (\Throwable $th) {
                    echo $th->getMessage();  
                    echo "<div class='error'><p>ERROR</p></div>";
                }
            }
        ?>
    </div>
    <!--Funcion 1-->
    <div>
        <h3>Funcion 1</h3>

        <div class="div_insert">
            <?php
                echo "
                <form  method='post' action='".$_SERVER['PHP_SELF']."'>
                    <label class='labelname'>DNIs</label>
                    <input type='text' class='entrada' name='dni'>

                    <button class='btn' name='FNC_04'>Enviar</button>
                </form>";
            ?>
        </div>
        <?php
            if(isset($_POST['FNC_04'])){
                $dni=$_POST['dni'];
                
                try {
                    $sql = $db-> prepare("SELECT EJ_04(:dni)");
                    $sql -> bindValue(':dni', $dni);

                    $sql->execute();

                    $row = $sql->fetch(); 


                    echo "<table class='table_consulta'>";
                    echo "<tr>";
                    echo "<th>Cantidad</th>";
                    echo "</tr>";

                   
                    echo "<tr>";
                    echo "<td>" .$row[0] ."</td>";
                    echo "</tr>";
                    echo "</table>";

                    
                    
                } catch (\Throwable $th) {
                    echo $th->getMessage();  
                    echo "<div>ERROR</div>";
                }
            }
        ?>
    </div>
</body>
</html>