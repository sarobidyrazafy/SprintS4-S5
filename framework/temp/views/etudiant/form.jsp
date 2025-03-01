<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="mg.itu.model.Etudiant" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%
    Cookie[] cookies = request.getCookies();
    String errorJson = null;
    String link = (String) request.getAttribute("action");
    String objetJson = null;
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("errorData".equals(c.getName())) {
                errorJson = URLDecoder.decode(c.getValue(), "UTF-8");
            }
            if ("objetData".equals(c.getName())) {
                objetJson = URLDecoder.decode(c.getValue(), "UTF-8");
            }
        }
    }

    // Ensuite, d\u00E9s\u00E9rialiser et utiliser ces donn\u00E9es
    Gson gson = new Gson();
    HashMap<String, List<String>> error = gson.fromJson(errorJson, HashMap.class);
    Etudiant objet = gson.fromJson(objetJson, Etudiant.class);

%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>JSP page</title>
    <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <% if (error == null || error.isEmpty()) { %>
        <form action="<%= link %>" method="post">
            <label for="nom">Nom:</label>
            <input type="text" nom="nom" id="nom">
            <label for="prenom">Prenom:</label>
            <input type="text" name="prenom" id="prenom">
            <label for="user">User:</label>
            <input type="number" name="user" id="user">
            <label for="age">Age:</label>
            <input type="number" name="age" id="age">
            <%-- <label for="Naissance">Naissance:</label>
            <input type="date" name="naissance" id="Naissance"> --%>
            <input type="submit" value="Valider">
        </form>
    <% } else { %>
        <form action="<%= link %>" method="post">
            <% if (!error.get("nom").isEmpty()) { %>
                <% for (String errorMessage : error.get("nom")) { %>
                    <p><%= errorMessage %></p>
                <% } %>
            <% } %>
            <label for="nom">Nom:</label>
            <input type="text" name="nom" id="nom" value="<%= objet.getNom() != null ? objet.getNom() : "" %>">
            <% if (!error.get("job").isEmpty()) { %>
                <% for (String errorMessage : error.get("job")) { %>
                    <p><%= errorMessage %></p>
                <% } %>
            <% } %>
            <label for="prenom">Prenom:</label>
            <input type="text" name="prenom" id="prenom" value="<%= objet.getPrenom() != null ? objet.getPrenom() : "" %>">
            <% if (!error.get("user").isEmpty()) { %>
                <% for (String errorMessage : error.get("user")) { %>
                    <p><%= errorMessage %></p>
                <% } %>
            <% } %>
            <label for="user">User:</label>
            <input type="number" name="user" id="user" value="<%= objet.getUser() != 0 ? objet.getUser() : 0 %>">
            <% if (!error.get("salaire").isEmpty()) { %>
                <% for (String errorMessage : error.get("salaire")) { %>
                    <p><%= errorMessage %></p>
                <% } %>
            <% } %>
            <label for="age">Age:</label>
            <input type="number" name="age" id="age" value="<%= objet.getAge() != 0.0 ? objet.getAge() : 0 %>">
            <input type="submit" value="Valider">
        </form>
    <% } %>
</div>
<script src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
