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
        <label for="email">Email:</label>
        <input type="text" name="email" id="email"> <br>
        <label for="mdp">Mot de passe:</label>
        <input type="text" name="mdp" id="mdp"> <br>
        <input type="submit" value="Valider">
    </form>
</div>
<!-- Bootstrap JS (optional, but needed for some features) -->
<script src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
