<html>
 <head>
  <script src="jquery.js"></script>
  <script type="text/javascript">
   function analyseRequest ( request ) {
     // 
     if ( request != "" ) {
       var retJSONObject = JSON.parse( request );
       if ( retJSONObject["command"] != "" ) {
         var e = document.getElementById('command');
         e.value += "\n" + retJSONObject["command"];
  	 e.scrollTop = e.scrollHeight;;
       }
     }
   }

   function getCommandRequest (  ) {
     jqueryReaktion ( "jqueryRequest.php?getCommandRe=1", analyseRequest );
   }

   function keepAliveReaktion () {
     getCommandRequest();
     setTimeout("keepAliveReaktion()",(1000 * 1));
   }
  </script>

 </head>
 <body onload="keepAliveReaktion()">
  <?php
    require_once ("functions.php");
    $text = "";

    if ( isset ($_POST["command"]) ) {
      $text .= $_POST["command"];
    }

    if ( isset ( $_POST["commandLine"] ) ) {
      // Command are insert
      setCommandForDevice ($_POST["commandLine"]);
      $text .= "\n]\$ " . $_POST["commandLine"];
    } else {
      echo "No command";
    }

    // Show Page for Browser
    //$text = "";
    $result = getRequestFromDevice();
    //print_r ( $result );
    if ( !empty ($result) )
      $text .= "".$result[0]["command"];
    ?>
      <form name="" method="post" action="db.php">
       <textarea id="command" name="command" cols="100" rows="20"><?php echo $text; ?></textarea>
       <br />
       <input type="text" size="80" name="commandLine">
       <input type="submit" value="Senden">
      </form>
      <?php
  ?>
  <script type="text/javascript">
   var e = document.getElementById('command');
   e.scrollTop = e.scrollHeight;;
  </script>
 </body>
</html>
