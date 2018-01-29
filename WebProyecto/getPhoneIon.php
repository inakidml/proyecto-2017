<?php
  header('Access-Control-Allow-Origin: *');
	$url = "http://10.1.3.14:8080/rest/items/IonPhone/state";
    
      $options = array(
        'http' => array(
            'header'  => "Content-type: text/plain\r\n",
            'method'  => 'GET'
        ),
      );
    
      $context  = stream_context_create($options);
      $result = file_get_contents($url, false, $context);
    
      echo json_encode($result,true);
?>