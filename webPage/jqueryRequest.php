<?php
  require_once ("functions.php");
  $devID = 0;

  if ( isset ($_GET["devID"]) ) {
    $devID = $_GET["devID"];
  }

  if ( isset ( $_GET["getCommandRe"] ) ) {
    //
    $result  = getRequestFromDevice( $devID );
    if ( !empty ( $result ) )
      $commRes["command"] = $result[0]["command"];
    else 
      $commRes["command"] = "";

    echo (json_encode ( $commRes ) );
  }

?>
