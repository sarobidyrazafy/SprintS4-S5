<%@page import="mg.itu.controller.EmpController"%>
<%@page import="java.util.ArrayList"%>
<%
    String link = (String)request.getAttribute("action");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>JSP page</title>
    <!-- Bootstrap CSS -->
    <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>Formulaire Employe</h1>
    <form action="<%= link %>" method="post">
        <label for="name">Nom:</label>
        <input type="text" name="nom" id="nom"> <br>
        <label for="job">Prenom:</label>
        <input type="text" name="prenom" id="prenom"> <br>
        <label for="salaire">age:</label>
        <input type="number" name="age" id="age"> <br>
        <input type="submit" value="Valider">
    </form>
</div>
<!-- Bootstrap JS (optional, but needed for some features) -->
<script src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
