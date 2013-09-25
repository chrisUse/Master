var xmlHttpObject;

if (typeof XMLHttpRequest != 'undefined') {
  xmlHttpObject = new XMLHttpRequest();
}

if (!xmlHttpObject ) {
  try {
    xmlHttpObject = new ActiveXObject("Msxml2.XMLHTTP");
  } catch(e) {
    try {
      xmlHttpObject = new ActiveXObject("Microsoft.XMLHTTP");
    } catch(e) {
      xmlHttpObject = null;
    }
  }
}

function jqueryReaktion ( url, functionToCall ) {
  xmlHttpObject.open('get', url);
  xmlHttpObject.onreadystatechange = function () {jqueryReaktionHandle (functionToCall);} ;
  xmlHttpObject.send(null);
}

function jqueryReaktionHandle ( functionToCall ) {
  if (xmlHttpObject.readyState == 4) {
    functionToCall ( xmlHttpObject.responseText );
  }
}


