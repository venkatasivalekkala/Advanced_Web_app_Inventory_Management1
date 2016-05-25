<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<title>Order Form</title>
<meta http-equiv="content-type" 
		content="text/html;charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/jadrn013/proj3_css/form_style.css" />   
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="/jadrn013/proj3_js/validation.js"></script>
    <script type="text/javascript" src="/jadrn013/proj3_js/shopping_cart_helper.js"></script>
</head>
    
<body>
<div id="checkout_container"> 
	<div id="banner">
		<h1>Snapit Cameras</h1>
	</div> 
	<div class=top></div>
<div id="container">
  <form  
              name="BillingForm"
             <%-- onsubmit="return validateform()" --%>
              action="/jadrn013/servlet/OpenCheckout"
              method="post">
	
	
	
	<fieldset>
        <legend>Summary</legend>
	    <%@ page import ="java.util.*" %>
    <%
List<String> orderDetails = (List)request.getAttribute("summary");
   
    
    %>
    <p id="subtotalOrder">Sub Total: $<% out.println(orderDetails.get(0)); %></p>
     <p id="taxOrder">Tax: $<% out.println(orderDetails.get(1)); %></p>
      <p id="shipOrder">Shipping: $5</p>
     <p>&nbsp;</p>
      <p id="totalOrder"> Total: $<% out.println(orderDetails.get(2)); %></p>
	</fieldset>

    <fieldset>
        <legend>Billing Address</legend>
        <ul class="inlineobjects">
            <li><label class="title">Name:</label></li>
            
            <li><label class="listitem">First Name<span class="astric">*</span></label>
                <input type="text" name="FirstName" id="firstname" size="25" />
            </li>       

            <li><label class="listitem">Middle Name</label>
                <input type="text" name="MiddleName" id="middlename" size="25" />
            </li>  
 
            <li><label class="listitem">Last Name<span class="astric">*</span></label>
                <input type="text" name="LastName" id="lastname" size="25" />
            </li> 
        </ul>
                
        <ul class="inlineobjects"> 
            <li><label class="title">Address:</label></li>
            <li><label class="listitem">Address 1<span class="astric">*</span></label>
            <input type="text" name="Address1" id="address1" size="25" />
            </li> 
     
            <li><label class="listitem">Address 2</label>
            <input type="text" name="Address2" id="address2" size="25" /></li>
            
            <li><label class="listitem">City<span class="astric">*</span></label>
            <input type="text" name="City" id="city" size="20" /> </li>
            
            <li><label class="listitem">State<span class="astric">*</span></label>
            <input type="text" title="Example: CA/ca for California" name="State" id="state" size="2" maxlength="2" /></li>
            
            <li><label class="listitem">Zip<span class="astric">*</span></label>
            <input name="Zip" id="zipcode" size="5" maxlength="5" /></li>
        </ul>
     
        <ul class="inlineobjects">   
            <li><label class="title">Phone Number:</label></li>
            <li><label class="listitem">Home Phone<span class="astric">*</span></label>
                (<input type="text" name="Area_Phone" size="3" id="hmphone1" maxlength="3" /> )
                <input type="text" name="Prefix_Phone" size="3" id="hmphone2" maxlength="3" />
                <input type="text" name="Phone" size="4" id="hmphone3" maxlength="4" /></li>     
        </ul>
	
        <div id="message">&nbsp;</div>                 
        </fieldset>
        
        <fieldset>
        <legend>Shipping Address</legend>
        <input id="checkboxCenter" type="checkbox" name="shipping address is same as billing address" value="shipAddSameAsBill" >
        <label class="boldCheckbox">shipping address is same as billing address</label>
        
            <ul class="inlineobjects">
                <li><label class="title">Name:</label></li> 
                
                <li><label class="listitem">First Name<span class="astric">*</span></label>   
                <input type="text" name="ShippingFirstName" id="shippingfirstname" size="25" /></li>
                
                <li><label class="listitem">Middle Name</label>
                    <input type="text" name="ShippingMiddleName" id="shippingmiddlename" size="25" /></li>  
 
                <li><label class="listitem">Last Name<span class="astric">*</span></label>
                    <input type="text" name="ShippingLastName" id="shippinglastname" size="25" /></li> 
            </ul>  
  
           <ul class="inlineobjects"> 
            <li><label class="title">Address:</label></li>
            <li><label class="listitem">Address 1<span class="astric">*</span></label>
            <input type="text" name="ShippingAddress1" id="shippingaddress1" size="25" />
            </li> 
     
            <li><label class="listitem">Address 2</label>
            <input type="text" name="ShippingAddress2" id="shippingaddress2" size="25" /></li>
            
            <li><label class="listitem">City<span class="astric">*</span></label>
            <input type="text" name="ShippingCity" id="shippingcity" size="20" /> </li>
            
            <li><label class="listitem">State<span class="astric">*</span></label>
            <input type="text" title="Example: CA/ca for California" name="ShippingState" id="shippingstate" size="2" maxlength="2" /></li>
            
            <li><label class="listitem">Zip<span class="astric">*</span></label>
            <input name="ShippingZip" id="shippingzipcode" size="5" maxlength="5" /></li>
        </ul>
     
        <ul class="inlineobjects">   
            <li><label class="title">Phone Number:</label></li>
            <li><label class="listitem">Home Phone<span class="astric">*</span></label>
                (<input type="text" name="Ship_Area_Phone" size="3" id="tel1" maxlength="3" /> )
                <input type="text" name="Ship_Prefix_Phone" size="3" id="tel2" maxlength="3" />
                <input type="text" name="Ship_Phone" size="4" id="tel3" maxlength="4" /></li>     
     
        </ul>
        <div id="shipping_message">&nbsp;</div>  
        </fieldset>   
     
         <fieldset>
        <legend>Payment Type</legend>
        
        	<ul class="inlineobjects">
                <li><label class="title">Card Type:<span class="astric">*</span></label></li> 
                <select  id="cardtype">
                	<option value="">select</option>
 					 <option value="visa">Vise</option>
  					 <option value="mastercard">Master Card</option>
  					 <option value="discover">Discover</option>
  					 <option value="americanexpress">American Express</option>
				</select>
            </ul>
        	
        	
            <ul class="inlineobjects">
                <li><label class="title">Payment:</label></li> 

                <li><label class="listitem">Name on card<span class="astric">*</span></label>   
                <input type="text" name="NameOnCard" id="nameoncard" size="25" /></li>
                
                <li><label class="listitem">Card number</label>
                    <input type="text" name="CardNumber" id="cardnumber" maxlength="16" /></li>  
                <li><label class="title"> </label></li> 
                <li><img id="card" class="listitem" alt="PaymentCard" src="/~jadrn013/proj3/images/cards.jpg" /></li>
            </ul>
             
             <ul class="inlineobjects">
                 <li><label class="title">Expiration Date:</label></li>
                <li><label class="listitem">mm<span class="astric">*</span></label>
                    <input name="Month" id="month" size="5" maxlength="2" /></li>
                 
                <li><label class="listitem">yyyy<span class="astric">*</span></label>
                    <input name="Year" id="year" size="5" maxlength="4" /></li>
      </ul> 
      
      <ul class="inlineobjects">
                <li><label class="title">Security Code:</label></li> 

                <li><label class="listitem">Security Code<span class="astric">*</span></label>   
                <input type="text" name="SecurityCode" id="securitycode" size="25" /></li>
            </ul>

             <div id="payment_message">&nbsp;</div> 
        </fieldset>
        
        <div id="button">  
        <input type="submit" value="Submit" name="submit" class="formbutton" />
        <input type="reset" value="Reset" name="reset" class="formbutton" />
	<a href="http://jadran.sdsu.edu/jadrn013/proj3.html">  <input type="button" value="Home" name="Home" class="formbutton" /></a>
       
	</div>   
     
</form>      
</div> 
</div>
</body>
</html>
