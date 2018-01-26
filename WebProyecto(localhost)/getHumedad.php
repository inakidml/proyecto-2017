<?php
header('Content-Type: application/json');

$query = $_GET['sql'];

$con = mysqli_connect("localhost", "root", "root", "Proyecto2018_db");
if (mysqli_connect_errno($con)) {
    echo "Failed to connect to DataBase: " . mysqli_connect_error();
} else {
    $data_points = array();
    $result = mysqli_query($con, $query);
    while ($row = mysqli_fetch_array($result)) {
        $point = array("fecha" => $row['fecha'], "humedad" => $row['humedad']);
        array_push($data_points, $point);
    }
    echo json_encode($data_points,true);
}
mysqli_close($con);
?>