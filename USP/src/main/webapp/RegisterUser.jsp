<%--
  Created by IntelliJ IDEA.
  User: Angel
  Date: 4/9/2021
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">
    function validate_Login() {
        var idName = document.getElementById("idName"); // tezi id-ta ot samata funkciq(getElementById) trqbva da se slojat v input-textovete
        var idEmail = document.getElementById("idEmail");
        var idPhone = document.getElementById("idPhone");
        var idAge = document.getElementById("idAge");
        var idPassword = document.getElementById("idPassword");
        let regexName = new RegExp("[A-Za-z]{5,20}");
        let regexEmail = new RegExp("/^w+([.-]?w+)*@w+([.-]?w+)*(.w{2,3})+$/");
        let regexPhone = new RegExp("[0-9]{10}");
        let regexAge = new RegExp("[0-9]{1,2}");
        let regexPassword = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");
        if (!idName.value.match(regexName)) {
            alert("Invalid Name")
        }
        if(!idEmail.value.match(regexEmail)){
            alert("Invalid Email")
        }
        if(!idPhone.value.match(regexPhone)){
            alert("Invalid Phone");
        }
        if(!idAge.value.match(regexAge)){
            alert("Invalid Age")
        }
        if(!idPassword.value.match(regexPassword)){
            alert("Invalid Password")
        }
    }
</script>
<%--
<form action="RegistrationServlet" method="post">
<input type="submit" name="registration" value="Registrirai se">
</form>
--%>
</body>
</html>
