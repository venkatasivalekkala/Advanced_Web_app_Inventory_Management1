

$(document).ready( function() {
    $("[name='username']").focus();
    
    $(':submit').on('click', function(e) {
        if($("[name='username']").val().trim() == "") {
            $('#error').text('Please enter your username.');
            $("[name='username']").focus();
            e.preventDefault(); 
            }
        else if($("[name='password']").val().trim() == "") { 
            $('#error').text('Please enter your password.');
            $("[name='password']").focus();
            e.preventDefault(); 
            }         
        else
            return;
        });      
    });
    
