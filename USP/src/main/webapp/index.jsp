<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">
    function selectFromCombo(){ //chrez tazi funkciq vzemame dvete izbrani stoinosti syotvetno ot dvata combobox-a i setvame na skriti input
        // poleta(hidden),ot koito shte vzemem stoinostite i shte gi setnem v servleta.
var city=document.getElementById("cities");
var movie=document.getElementById("movies");
var selectCity=city.options[city.selectedIndex].value;
var selectMovie=movie.options[movie.selectedIndex].value;
document.getElementById("city").value=selectCity;
document.getElementById("movie").value=selectMovie
    }
</script>
<h1>Dobre doshli ${Name}</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<%-- TOVA SA SAMITE COMBOBOX-VE SYOTVETNO ZA DVATA LISTA.

<form action="HelloServlet" method="post"> -> Tova shte e glavnata forma koqto izpylnqva glavniqt servlet- HelloServlet i sprqmo butonite shte ni nasochva kym drugi servleti.

-> tuk predpolagam shte imame nqkakauv dizain za headera
            <input type="submit" name="vhod" value="vhod">
            <input type="submit" name="kina" value="Kina">

 Изберете град: &nbsp
            <select name="cities" id="cities">
                <c:forEach var="city" items="${cityList}">
                    <option value="${city.city_movie}">${city.city_movie}</option>
                    </option>
                </c:forEach>
            </select>
 Изберете филм: &nbsp
            <select name="movies" id="movies">
                <c:forEach var="movie" items="${MovieList}">
                    <option value="${movie.name_movie}">${movie.name_movie}</option>
                    </option>
                </c:forEach>
            </select>
            <input type="text" name="dateReservation" id="dateReservation">
            или
            <input type="text" mame="nameMovie" id="nameMovie">
            <input type="submit" name="search" value="tursi">
            <input type="hidden" name="city" id="city">
            <input type="hidden" name="movie" id="movie">
--%>
</body>
</html>
