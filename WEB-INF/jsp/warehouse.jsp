<!doctype html>
<html lang="us">
<head>
  <meta charset="utf-8">
   <title>Warehouse Inventory</title>
    <link rel="stylesheet" href="../css/styles.css">
  <script type="text/javascript" src="/jquery/jquery.js"></script>
  <script type="text/javascript" src="/jadrn013/js/validation.js"></script>
</head>
<body>
  <h1>Merchandise Inventory</h1>
  <div id="tabs">
    <ul id="menu">
      <li><a href="#tabs-1">Inventory Received</a></li>
      <li><a href="#tabs-2">Inventory Sent Out</a></li>
      <li><input type="button" value="LOGOUT" id="logout"/></li>
    </ul>
    <div id="tabs-1"></div>
    <div id="tabs-2"></div>
  </div>

    <script src="/jquery/jQueryUI.js"></script>
    <script>
    $( "#tabs" ).tabs();
    </script>
  </body>
  </html>
