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
    function validate(){
        var idEmail=document.getElementById("idEmail").value; // tezi id-ta ot samata funkciq(getElementById) trqbva da se slojat v input-textovete
        var idPassword=document.getElementById("idPassword").value;
        let regexEmail=new RegExp('/^w+([.-]?w+)*@w+([.-]?w+)*(.w{2,3})+$/'); // ili s edinichni kavichki ili dvoini
        let regexPassowrd=new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})"); // vaidate pass
        if(!idEmail.value.match(regexEmail)){
            alert("Invalid Email");
        }
        if(!idPassword.value.match(regexPassowrd)){
            alert("Invalid Password");
        }
    }
</script>
<%--
<form action="LoginServlet" method="post">
<input type="submit" name="vhod" value="VHOD">
<input type="submit" name="registraciq" value="Nova Registraciq">
</form>
--%>
</body>
</html>
