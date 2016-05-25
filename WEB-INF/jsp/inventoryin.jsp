<form method="post" id="form" class=“inventoryRecived” action="">
  <fieldset>
	
    <div id="table">

	<div id="message_line">&nbsp;</div>
        <div >
	<ul class="inlineobjects">

           <li> <label class=“title” >SKU<span class="astric">*</span></lable></li>
		<li><input type="text" name="sku" id="sku" size="7" placeholder="AAA-111"/></li>
	</ul>
	<ul class="inlineobjects">
            <li><label class=“title”>Quantity <span class="astric">*</span></lable></li>
		<li><input type="text" name="qty" id="qty" min="1" size="10"/></li>
	</ul>

	<ul class="inlineobjects">
           <li> <label class=“title”> Date </lable> </li>
		<li><input type="text" name="date" id="date" size="10" placeholder="mm/dd/yyyy" /></li> 
</ul>

        </div>
        <div  id="data">
       
	<ul class="inlineobjects"> 
            <li><label class=“title”>Vendor: </lable></li>
		<li><span id="vendor"></span></li><br/>
	</ul>
      
    	 <ul class="inlineobjects"> 
            <li><label class=“title”>Category: </lable></li>
		<li><span id="category"></span></li><br/>
	</ul>
     
   	<ul class="inlineobjects"> 
            <li><label class=“title”>Manufacture ID:</lable></li>
		<li> <span id="mid"></span></li></br>
	</ul>
         
    	<ul class="inlineobjects"> 
          <li>  <label class=“title”>In Stock: </lable></li>
		<li><span id="stock"></span></li><br/>
	</ul>
  
        </div>

      <div id="img">
	<ul class="inlineobjects" id="imageWrapper_in"> 
        <li><img id="pic"  style="height:150px;" /></li></ul>
      </div>
    </div>
  </fieldset>
  <div id="button_panel">
    <input type="reset" value="Clear" id="reset"/>
    <input type="button" value="Add Merchandise" id="submit"/>
  </div>
</form>