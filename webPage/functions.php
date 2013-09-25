<?php
    /*
     create database chrisMaster;
     use chrisMaster;
     create table chrisMaster (id int UNSIGNED NOT NULL AUTO_INCREMENT, devID int, command VarChar(512), typ boolean, cID int, PRIMARY KEY (id));
     insert into chrisMaster (devID, command, typ, cID) VALUES (1, "ls", 0, 0);
    */

    $link = NULL;

    function connectDB () {
      $link = mysql_connect('localhost', 'root', '');
      if (!$link) {
 //     die('Verbindung schlug fehl: ' . mysql_error());
        return (NULL);
      }
      //echo 'Erfolgreich verbunden';

      mysql_select_db("chrisMaster");
      return ($link);
    }

    function closeDB ( $link ) {
      mysql_close($link);
    }

    function insertInDB ( $sql ) {
      $link = connectDB ();
      $ret = false;
      $result = mysql_query ( $sql );
      if ($result) {
        $ret = true;
      } 

      closeDB ( $link );
      return $ret;
    }

    function getDBArray ( $sql ) {
      $link = connectDB ();
      $retArray = array ();
      //echo "SQL: " . $sql;

      $result = mysql_query ( $sql );
      if ($result) {
        $i=0;
        while ($row = mysql_fetch_assoc($result)) {
          //echo "..";
          $retArray[$i] = $row;
          $i++;
        }
      }
      closeDB ( $link );

      return $retArray;
    }

    function getCommandForDevice () {
      return getDBArray ("select * from chrisMaster "
                        ."where devID=1 and typ=0 order by id LIMIT 1"); // desc
    }

    function getRequestFromDevice() {
      $retArray = getDBArray ("select * from chrisMaster "
                 ."where devID=1 and typ=1 order by id LIMIT 1");
      if ( !empty ($retArray) )
        insertInDB ("update chrisMaster set typ=3 where id=".$retArray[0]["id"]);
      return $retArray;
    }

    function setRequestedCommand ( $devID, $command, $cID ) {
      $sql = ""; 
      $checkElem = getDBArray ("select id from chrisMaster where command LIKE '".$command."' AND cID=".$cID);
      if ( empty ($checkElem) ) {
        insertInDB ("insert into chrisMaster (devID, command, typ, cID) VALUES (".$devID.", \"".$command."\", 1, ".$cID.");");
	insertInDB ("update chrisMaster set typ=2 where id=".$cID);
      }
    }

    function setCommandForDevice ( $command ) {
      //$checkElem = getDBArray ("select id from chrisMaster where command LIKE '".$command."' AND cID=".$cID);
      //if ( empty ($checkElem) ) {
      echo "Command: " . $command ;
      insertInDB ("insert into chrisMaster (devID, command, typ, cID) VALUES (1, \"".$command."\", 0, 0);");
      //}
    }


?>
