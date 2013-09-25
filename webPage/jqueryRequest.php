<?php
  require_once ("functions.php");

  if ( isset ( $_GET["getCommandRe"] ) ) {
    //
    $result  = getRequestFromDevice();
    if ( !empty ( $result ) )
      $commRes["command"] = $result[0]["command"];
    else 
      $commRes["command"] = "";

    echo (json_encode ( $commRes ) );
  }

?>
