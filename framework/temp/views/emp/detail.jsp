<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
  
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
    <h2>Bien reçu!!</h2>
    Nom: <%=emp.getNom()%> Prenom: <%=emp.getPrenom()%> Age: <%= emp.getAge()%>
</body>
</html>