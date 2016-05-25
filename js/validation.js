$(document).ready(function(){
	history.go(1);
	var flag=0;
	$('#ui-id-1').addClass("selected");
	reset(flag);

	$('#ui-id-1').click(function(){

		$('#ui-id-1').addClass("selected");
         	$('#ui-id-2').removeClass("selected");	
		flag = 0;
		reset(flag);
	});

	$('#ui-id-2').click(function(){
		$('#ui-id-2').addClass("selected");
         	$('#ui-id-1').removeClass("selected");	
		flag = 1;
		reset(flag);
	});

	$('#logout').click(function(){
		var ran = Math.random();
		$.post("/jadrn013/servlet/DispatchServlet?action=logout", function(response){});
		jQuery.get("/jadrn013/logout.txt", function(data){
			$('body').html(data);
		});
		window.setTimeout(function(){location.replace("http://jadran.sdsu.edu/jadrn013/login.html")}, 800);
	});

});


function fetchSKU(){
	var formData = $('#sku').serialize();
	$.post("/jadrn013/servlet/DispatchServlet?action=dbTest", formData, function(response){
		if(response == -1){
			$('#message_line').text("SKU does not exist!");
			$('#sku').addClass("form_error");
		}
		else{
			$('#message_line').empty();
			$('#sku').removeClass("form_error");
			populateData(response);
		}
	});
}



function isValidQuantity(){
	var qty = $('#qty').val();
	if(qty > 0 && qty % 1 == 0){
		$('#qty').removeClass("form_error");
		$('#message_line').empty();
		return true;
	}
	else{
		$('#message_line').text("Quantity must be a positive whole number");
		$('#qty').addClass("form_error");
		return false;
	}
}



function isLetter(str){
	if(str.match(/[A-Z]/i))
		return 1;
}

function isValidDate(){
	var correctDate = 0;
	var currentYear = (new Date).getFullYear();
	var date = $('#date').val().split("/");
	var month = parseInt(date[0], 10);
	var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
	if (date[2] % 400 == 0 || (date[2] % 100 != 0 && date[2] % 4 == 0))
		monthLength[1] = 29;
	if($('#date').val().length != 10) correctDate = 4;
	else if(date.length != 3) correctDate = 4;
	else if(date[0]<1 || date[0]>12 || date[0]%1!=0) correctDate = 0;
	else if(date[1]<1 || date[1]>31 || date[1]%1!=0) correctDate = 1;
	else if(date[2]<1 || date[2]>currentYear || date[2]%1!= 0) correctDate = 2;
	else correctDate = 3;

	if (correctDate == 3){
		$('#date').removeClass("form_error");
		$('#message_line').empty();
		return true;
	}
	else if (correctDate == 4){
		$('#message_line').text("Invalid date format. Correct format should be: mm/dd/yyyy");
		$('#date').addClass("form_error");
		return false;
	}
	else if (correctDate == 0){
		$('#message_line').text("Invalid month. Month should range from 1 to 12");
		$('#date').addClass("form_error");
		return false;
	}
	else if (correctDate == 1){
		$('#message_line').text("Invalid day. Day should range from 1 to 31");
		$('#date').addClass("form_error");
		return false;
	}
	else{
		$('#message_line').text("Invalid year. Year should not be later than current year");
		$('#date').addClass("form_error");
		return false;
	}
}


function reset(flag){
	if(flag==0){
			
		$('#tabs-2').empty();
		$.post("/jadrn013/servlet/DispatchServlet?action=add", function(data){
			$('#tabs-1').html(data);
			handler(flag);
		});
	}
	else{
		$('#tabs-1').empty();
		$.post("/jadrn013/servlet/DispatchServlet?action=remove", function(data){
			$('#tabs-2').html(data);
			handler(flag);
		});
	}
}

function handler(flag){
	$('#data').css("display","none");
	$('#sku').focus();
	$('#date').val($.datepicker.formatDate('mm/dd/yy', new Date()));
	$('#sku').on('keyup', function() {
		$('#sku').val($('#sku').val().toUpperCase());
	});

	$('#sku').blur(function(){
		if(isValidSKU()){
			fetchSKU();
		}
		else{
			$('#data, #pic').css("display","none");
			$('#sku').focus();
		}
	});

	$('#qty').blur(function(){
		isValidQuantity();
	});

	$('#date').blur(function(){
		isValidDate();
	});

	$('#submit').click(function(e){
		e.preventDefault();
		if(flag==0){
			addInventory();
		}
		else if(flag==1){
			removeInventory();
		}
	});

}


function addInventory(){
	if(isValidSKU() && isValidQuantity() && isValidDate()){
		var formData = $('#form').serialize();
		$.post("/jadrn013/servlet/DispatchServlet?action=addin", formData, function(response){
			if(response == -1){
				$('#message_line').text("Fatal Error!");
			}
			else{
				$('#message_line').text("New Inventory Successfully Added!");
				$('#data, #pic').css("display","none");
				$('#sku').focus();
			}
		});
	}
}

function removeInventory(){
	if(isValidSKU() && isValidQuantity() && isValidDate()){
		var formData = $('#form').serialize();
		$.post("/jadrn013/servlet/DispatchServlet?action=removeout", formData, function(response){
			if(response == -1){
				$('#message_line').text("Process Failed! Insufficient Inventory. Reduce the Quantity!");
			}
			else{
				$('#message_line').text("Inventory Successfully Removed!");
				$('#data, #pic').css("display","none");
				$('#sku').focus();
			}
		});
	}
}


function populateData(data){
	$('#data, #pic').css("display","inline");
	console.log(data);
	var a = $.parseJSON(data);
	var list = a[0];
	var categories = ["DSLR", "Point and Shoot", "Compact", "Super Zoom ", "Mirrorless", "Film", "Disposable","Rangefinder"];
	var vendors = ["Nikon", "Canon", "Leica", "Olympus", "Pentax", "Sony", "Panasonic","Casio","Kodak","Hasselblad"];
	$('#vendor').text(vendors[list[2]-1]);
	$('#category').text(categories[list[1]-1]);
	$('#mid').text(list[3]);
	$('#pic').attr("src","http://jadran.sdsu.edu/~jadrn013/proj1/images/u_load_images/"+list[8].slice(0,-4));
	getInStock();
}

function getInStock(){
	var formData = $('#sku').serialize();
	$.post("/jadrn013/servlet/CheckInStock", formData, function(response){
		console.log(response);
		$('#stock').text(response);
	});
}

function isValidSKU(){
	sku=$('#sku');
	var errorMessage = new Array(5);
	errorMessage[0] = "Please enter SKU number";
	errorMessage[1] = "SKU input must be seven characters long";
	errorMessage[2] = "First three SKU inputs must be capital letters";
	errorMessage[3] = "Third SKU inputs must be a dash";
	errorMessage[4] = "Last three SKU inputs SKU must be numeric";

	var SKU = $('#sku').val().split("");

	if( SKU.length ==0){
		sku.addClass("form_error");
		$('#message_line').text(errorMessage[0]);
		return false;
	}

	if( SKU.length != 7){
		sku.addClass("form_error");
		$('#message_line').text(errorMessage[1]);
		return false;
	}

	for(var i=0; i<=2; i++){
		if(isLetter(SKU[i])!=1){
			sku.addClass("form_error");
			$('#message_line').text(errorMessage[2]);
			return false;
		}
	}

	if(SKU[3].indexOf('-')===-1){
		sku.addClass("form_error");
		$('#message_line').text(errorMessage[3]);
		return false;
	}

	for(var i=4; i<=6; i++){
		if(!$.isNumeric(SKU[i])){
			sku.addClass("form_error");
			$('#message_line').text(errorMessage[4]);
			return false;
		}
	}
	sku.removeClass("form_error");
	$('#message_line').empty();
	return true;
}





