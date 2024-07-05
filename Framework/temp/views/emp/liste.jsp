<%@page import="mg.itu.controller.EmpController"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<EmpController> emps =(ArrayList<EmpController>) request.getAttribute("list");
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
    <table class="table" border="1">
        <thead>
        <tr>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Age</th>
        </tr>
        </thead>
        <tbody>
            <% for (EmpController emp : emps) { %>
                <tr>
                    <td><%=emp.getNom() %></td>
                    <td><%=emp.getPrenom() %></td>
                    <td><%=emp.getAge() %></td>
                </tr>
            <% } %>
        
        </tbody>
    </table>
</div>
<!-- Bootstrap JS (optional, but needed for some features) -->
<script src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
