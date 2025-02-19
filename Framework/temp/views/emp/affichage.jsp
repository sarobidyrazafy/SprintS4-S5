<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mg.itu.model.Employe" %>
<%
    MySession ses = (MySession) request.getAttribute("session");
    Employe employe = (Employe) request.getAttribute("employe");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Détails de l'Employé</title>
</head>
<body>
<h1>Détails de l'Employé</h1>
<p>Nom: <%= employe.getNom() %></p>
<p>Prénom: <%= employe.getPrenom() %></p>
<p>Âge: <%= employe.getAge() %></p>
<a href="emp/list">Retour à la liste des employés</a>
</body>
</html>
