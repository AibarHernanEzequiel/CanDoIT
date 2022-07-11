<%--
  Created by IntelliJ IDEA.
  User: ezequiel
  Date: 9/7/22
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body class="container">
<h1 class="text-center mt-4">Lista de Temperaturas</h1>
<div class="container card">
    <table class="table">

        <thead>
        <tr>
            <th scope="col">Provincia</th>
            <th scope="col">Ciudad</th>
            <th scope="col">Temperatura</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listaDeClimas}" var="clima">
            <tr>
                <td>${clima.province}</td>
                <td>${clima.name}</td>
                <td>${clima.weather.tempDesc}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
${nuevaLista}
</body>
</html>
