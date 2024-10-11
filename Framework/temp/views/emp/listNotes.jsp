<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mg.itu.model.Note" %>
<%
    ArrayList<Note> list_notes = (ArrayList<Note>) request.getAttribute("list_notes");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Notes</title>
</head>
<body>
<h1>Liste des Notes</h1>
<table border="1">
    <tr>
        <th>Matiere</th>
        <th>Note</th>
    </tr>
    <%
        for (Note note : list_notes) {
    %>
    <tr>
        <td><%= note.getMatiere() %></td>
        <td><%= note.getNote() %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
