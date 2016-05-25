<!DOCTYPE html>
<html>
<head>
    <title>Merchandise Login</title>
    <meta http-equiv=s"content-type" 
		content="text/html;charset=utf-8" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="expires" content="0" />        
    
    <script type="text/javascript" src="/jquery/jquery.js"></script>
    <script type="text/javascript" src="/jadrn013/js/login.js"></script>
    <link rel="stylesheet" type="text/css" href="/jadrn013/css/login.css" />    
</head>

<body>
    <div>
        <h2>Merchandise</h2>
        <form method="post" 
            action="/jadrn013/servlet/Login">
        <table>
            <tr>
                <td><label>User Name:</label></td>
                <td><input type="text" id="user" name="user" /></td>
            </tr>
            <tr>
                <td><label>Password:</label></td>
                <td><input type="password" id="passwd" name="passwd" /></td>
            </tr>
            <tr>
                <td><input class="button" type="reset" /></td>
                <td><input class="button" type="submit" value="Log In" /></td>
            </tr>
        </table>
        </form>
        <div id="error">Invalid Username or Password
        </div>
    </div>   
</body>

</html>