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
     <script type="text/javascript" src="/jadrn013/proj3_js/floating_cart.js"></script>
    <script type="text/javascript" src="/jadrn013/proj3_js/show_product.js"></script>
    <script type="text/javascript" src="/jadrn013/proj3_js/shopping_cart_helper.js"></script>
    
    
</head>

<body>
<div id="container"> 
	<div id="banner">
		<h1>Snapit Cameras</h1>
		<div id="cart">
       		<img src="/~jadrn013/proj3/images/cart.png" alt="shopping cart" id="cartImage"/>
       	</div>
	</div> 
	<div id="checkout_count"></div> 
	
	    <ul id = "menu">
        	<li><a id="products" class="selected" href="/jadrn013/proj3.html" >Products</a></li>
         	<li><a id="aboutus" href="/jadrn013/aboutus.html">About Us</a></li>
         	<li><a id="contact" href="/jadrn013/contactus.html">Contact</a></li>       
       </ul>
    
	<div id="right_column">

	</div>
    
<div id="middle_column">
<%@ page import ="java.util.*" %>
<%
	List<String> product = (List)request.getAttribute("Product");
%>
	<h2><%= product.get(0) %> - <%= product.get(2) %></h2>
	<div id="image"><img src="/~jadrn013/proj1/images/u_load_images/<%= product.get(6) %>"></div>
	
	<div id="details">
	<input type="hidden" id="sku" value=<%= product.get(8) %> />
	
	<p class="name">Category: <%= product.get(1) %></p>
	<p class="name">Description: <%= product.get(3) %></p>
	<p class="name">Features: <%= product.get(4) %></p>
	<p class="name">price: <%= product.get(5) %></p>
	<p id="on_hand"><%= product.get(7) %></p>
	

	<% if(product.get(7) == null)
	{ %>
	<input type="submit" value="Add to Cart" name="addtocart" class="disabled"  disabled/>
	<p>coming soon</p>
	<% } else if(product.get(7).trim().equals("0")) { %>	
	<input type="submit" value="Add to Cart" name="addtocart"  class="disabled" disabled/>
	<p>More on the way</p>
	<% }else{ %>
	<input type="submit" value="Add to Cart" id="addToCart" name="addtocart" class="addtocart" />
	<%}%>
	</div>
    </div>
    
    
    	<div id="data_column">
	<div id="checkout_button_column"> </div>
	<div id="checkout_data_column"> </div>
</div>
</body>

</html>
