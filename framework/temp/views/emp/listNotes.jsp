<%@page import="mg.itu.model.Note"%>
<%@page import="mg.itu.model.Utilisateur"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Note> list_notes =(ArrayList<Note>) request.getAttribute("list_notes");
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
    <div class="mb-3">
        <a href="/Sprint_All/emp/login?id=0" class="btn btn-primary">Login</a>
    </div>
    <table class="table" border="1">
        <thead>
        <tr>
            <th>Nom</th>
            <th>Matière</th>
            <th>Note</th>
        </tr>
        </thead>
        <tbody>
            <% if (list_notes != null) { 
                for (Note n : list_notes) { %>
                    <tr>
                        <td><%=n.getUtilisateur().getNom() %></td>
                        <td><%=n.getMatiere() %></td>
                        <td><%=n.getNote() %></td>
                    </tr>
            <% } } %>
        </tbody>
    </table>
</div>
<!-- Bootstrap JS (optional, but needed for some features) -->
<script src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
