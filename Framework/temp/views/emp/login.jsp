<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
</head>
<body>
<h1>Connexion</h1>
<form action="emp/traitlogin" method="post">
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>
    
    <label for="mdp">Mot de Passe:</label>
    <input type="password" id="mdp" name="mdp" required><br>
    
    <input type="submit" value="Se Connecter">
</form>
</body>
</html>
