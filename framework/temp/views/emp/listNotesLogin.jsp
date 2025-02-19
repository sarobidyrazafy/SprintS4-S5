<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mg.itu.model.Note" %>
<%
    MySession ses = (MySession) request.getAttribute("session");
    ArrayList<Note> list_notes = (ArrayList<Note>) request.getAttribute("list_notes");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Notes de l'Utilisateur</title>
</head>
<body>
<h1>Mes Notes</h1>
<table border="1">
    <tr>
        <th>Titre de la Note</th>
        <th>Contenu</th>
    </tr>
    <%
        for (Note note : list_notes) {
    %>
    <tr>
        <td><%= note.getTitre() %></td>
        <td><%= note.getContenu() %></td>
    </tr>
    <%
        }
    %>
</table>
<a href="emp/logout">Se Déconnecter</a>
</body>
</html>
