<?php
header('Content-Type: application/json');

$fecha = $_REQUEST['fec'];
echo json_encode("hola" + $fecha,true);

$con = mysqli_connect("10.1.100.100", "root", "root", "Proyecto2018_db");
if (mysqli_connect_errno($con)) {
    echo "Failed to connect to DataBase: " . mysqli_connect_error();
} else {
    $data_points = array();
    $result = mysqli_query($con, "SELECT fecha, temperatura FROM registro where fecha like '" + $fecha + "%'"); 
    while ($row = mysqli_fetch_array($result)) {
        $point = array("fecha" => $row['fecha'], "temperatura" => $row['temperatura']);
        array_push($data_points, $point);
    }
    echo json_encode($data_points,true);
}
mysqli_close($con);
?>