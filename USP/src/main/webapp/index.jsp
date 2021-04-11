<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<%-- TOVA SA SAMITE COMBOBOX-VE SYOTVETNO ZA DVATA LISTA.
 Изберете град: &nbsp
            <select name="cities">
                <c:forEach var="city" items="${cityList}">
                    <option>${city.city_movie}</option>
                    </option>
                </c:forEach>
            </select>
 Изберете филм: &nbsp
            <select name="movies">
                <c:forEach var="movie" items="${MovieList}">
                    <option>${movie.name_movie}</option>
                    </option>
                </c:forEach>
            </select>
--%>
</body>
</html>
