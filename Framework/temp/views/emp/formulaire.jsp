<%
    MySession ses = (MySession) request.getAttribute("session");
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Formulaire d'Employé</title>
</head>
<body>
<form action="emp/traitement" method="post">
    <label for="nom">Nom:</label>
    <input type="text" id="nom" name="nom" required><br>
    
    <label for="prenom">Prénom:</label>
    <input type="text" id="prenom" name="prenom" required><br>
    
    <input type="submit" value="Valider">
</form>
</body>
</html>
