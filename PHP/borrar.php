<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta http-equiv="refresh" content="10;url=Consultas.php">
    <link rel="stylesheet" href="style.css">

</head>
<body>
    <h1>PABLO SUAREZ ROMERO</h1>
    <?php
        require 'conectaDB.php'; 
        $db = conectaDB();

        $codHotel = $_POST['codH'];
        $habitacion = $_POST['numHabitacion'];

        try {
            $stm = $db->prepare('DELETE FROM habitaciones where codHotel = :codhotel AND numHabitacion = :habitacion'); 
            $stm -> bindParam(':codhotel', $codHotel);
            $stm -> bindParam(':habitacion', $habitacion);
            $stm -> execute();
    
            echo "<p>";
            echo "ELIMINADO EL HOTEL CON CODIGO" .$codHotel;
            echo "</p>";       
        } catch (\Throwable $th) {
            echo $stm . "<br>" . $e->getMessage();  
            echo "no se puede" ;
        }
        
        
    ?>
    <p>Redireccionando</p>


    
</body>
</html>