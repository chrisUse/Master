<html>
 <head>
   <?php 
     require_once ("functions.php");
     $newCommand = "";
     $commandID = 0;

     if (isset ($_GET["devID"])) {
      // The request is from Defivce
      // Set the request to db.
      if ( isset ($_GET["command"]) ) {
        $command = base64_decode ( $_GET["command"] );//$_GET["command"];//base64_decode ( $_GET["command"] );
        // Insert requested command in db
        echo "Recived Command: " . $command;
        setRequestedCommand ($_GET["devID"], $command, $_GET["cID"]);
      }
      // Generate the new command
      $result = getCommandForDevice($_GET["devID"]);
      //$commandID = $result[0]["id"];
      if ( !is_array ($result) || empty ($result) ) {
        // Lebenszeichen eintragen.
	setAlive ( $_GET["devID"] );
      } else {
        print_r ($result);
        $commandID = $result[0]["id"];
        $newCommand=urlencode(base64_encode($result[0]["command"]));//urlencode($result[0]["command"]);//base64_encode($result[0]["command"]);
      }
    } else {
    }

   ?>
   <!-- <meta http-equiv="refresh" content="0; URL=startonuri://callback/<?php echo $commandID; ?>/<?php echo $newCommand; ?>">  -->
 </head>
 <body>
 <a href="startonuri://callback/test321/test2">Load</a>
  <iframe src="startonuri://callback/<?php echo $commandID; ?>/<?php echo $newCommand; ?>" name="scheme" style="height: 15em;width: 100%; border: none; "></ iframe>
</body>
</html>
