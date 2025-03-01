<%@page import="mg.itu.controller.EtudiantController"%>
<%@page import="java.util.ArrayList"%>
<%
    String nom = (String) request.getAttribute("nom");
    String prenom = (String) request.getAttribute("prenom");
    double age = (double) request.getAttribute("age");
    int user = (int) request.getAttribute("user");
    
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <ul>
        <li>nom: <b><%= nom%></b></li>
        <li>prenom: <b><%= prenom%></b></li>
        <li>age: <b><%= age%></b></li>
        <li>User: <b><%= user%></b></li>
    </ul>
</body>
</html>