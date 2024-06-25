<%@page import="mg.itu.controller.EmpController"%>
<%@page import="java.util.ArrayList"%>
<%
    EmpController emp = (EmpController) request.getAttribute("employe");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultat</title>
</head>
<body>
    <ul>
        <li>Name: <b><%=emp.getNom()%></b></li>
        <li>Job: <b><%=emp.getPrenom()%></b></li>
        <li>Salaire: <b><%= emp.getAge()%></b></li>
    </ul>
</body>
</html>