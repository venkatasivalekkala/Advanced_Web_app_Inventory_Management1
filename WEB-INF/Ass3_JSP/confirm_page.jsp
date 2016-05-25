<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<title>Snapit Cameras</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	 <script src="../../../jquery/jquery.js"></script>
    <script src="../../../jquery/jQueryUI.js"></script>    
    <link rel="stylesheet" type="text/css" href="/jadrn013/proj3_css/styles.css" />
    <script type="text/javascript" src="/jadrn013/proj3_js/confirm_page.js"></script>
    <script type="text/javascript" src="/jadrn013/proj3_js/shopping_cart_helper.js"></script>
    
</head>

<body>
<div id="checkout_container"> 
	<div id="banner">
		<h1>Snapit Cameras</h1>
	</div>
	   <ul id = "menu">
        	<li><a id="products" class="selected" href="http://jadran.sdsu.edu/jadrn013/proj3.html" >Products</a></li>
         	<li><a id="aboutus" href="http://jadran.sdsu.edu/jadrn013/aboutus.html">About Us</a></li>
         	<li><a id="contact" href="http://jadran.sdsu.edu/jadrn013/contactus.html">Contact</a></li>       
       </ul> 

<!-- ********************************right column************************************** -->

	<div class="top"></div>

<%@ page import ="java.util.*" %>
<% List<String> product = (List)request.getAttribute("confirm"); %>
<div id="information_column"> 
<h2>Summary</h2>
<table id="summarytable">
<tr><td colspan="2">Sub Total: </td><td>$<%=product.get(4)%></td></tr>
<tr><td colspan="2">Tax: </td><td>$<%=product.get(5)%> </td></tr>
<tr><td colspan="2">Shipping: </td><td>$5 </td></tr>
<tr><td colspan="2">Total: </td><td>$<%=product.get(6)%></td></tr>
</table>

<h2>Shippng Address</h2>
 <div id = "shippingAddress">
 <p><%=product.get(0)%></p>
 <p><%=product.get(1)%></p>
 <p><%=product.get(2)%></p>
 <p><%=product.get(3)%></p>
 </div>

</div>
	<div id="checkout_data"> 

</div>
</div> 
</body>

</html>
