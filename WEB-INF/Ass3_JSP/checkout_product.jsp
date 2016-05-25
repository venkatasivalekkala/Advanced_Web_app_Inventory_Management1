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
    <script type="text/javascript" src="/jadrn013/proj3_js/checkout_product.js"></script>
    <script type="text/javascript" src="/jadrn013/proj3_js/shopping_cart_helper.js"></script>
    
</head>

<body>
<div id="checkout_container"> 
	<div id="banner">
		<h1>Snapit Cameras</h1>
	</div>  
    
<div class="top"></div>
<div id="information_column">
<%@ page import ="java.util.*" %>

<%
List<String> shippingDetails = (List)request.getAttribute("shippingDetails");
if(shippingDetails != null) {

%>
<h2>Summary</h2>
<table id="summarytable">
<tr><td colspan="2">Sub Total: </td><td>$<span id="subtotal"></span> </td></tr>
<tr><td colspan="2">Tax: </td><td>$<span id="tax"></span> </td></tr>
<tr><td colspan="2">Shipping: </td><td>$<span id="ship">5</span> </td></tr>
<tr><td colspan="2">Total: </td><td>$<span id="total"></span> </td></tr>
</table>

<h2>Shippng Address</h2>
 <div id = "shippingAddress">
 <p><% out.println(shippingDetails.get(0)); %></p>
 <p><% out.println(shippingDetails.get(1)); %></p>
 <p><% out.println(shippingDetails.get(2)); %></p>
 <p><% out.println(shippingDetails.get(3)); %></p>
 <p><% out.println(shippingDetails.get(4)); %></p>
 </div>
 
<% } %>
</div>

 <form 
 name="checkoutForm"             
action="/jadrn013/servlet/ConfirmPage"
method="post">
              
 <div id = "checkout_data"> </div>

<input type="hidden" name="subtotal" id="subTotal">
<input type="hidden" name="tax" id="Tax">
<input type="hidden" name="total" id="Total">
<input type="hidden" name="shippingName" value="<%=shippingDetails.get(0)%>" id="ShipName">
<input type="hidden" name="shipAddress" value="<%= shippingDetails.get(1) %>" id="ShipAddress">
<input id="shippingCity" type="hidden" name="shipCity" value="<%= shippingDetails.get(2) %>" id="ShipCity">
<input type="hidden" name="shipStateAndZip" value="<%= shippingDetails.get(3) %> - <%= shippingDetails.get(4) %>" id="ShipStateAndZip">
</form>

</div>
</body>
</html>
