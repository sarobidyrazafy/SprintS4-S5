<%@page import="mg.itu.controller.EmpController"%>
<%@page import="mg.itu.annotation.MySession"%>
<%@page import="java.util.ArrayList"%>
<%
    MySession ses = (MySession) request.getAttribute("session");
    ArrayList<EmpController> emps =(ArrayList<EmpController>) request.getAttribute("list");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>List Emp</title>
</head>
<body>
<%if (ses != null) { %>
    <form action="logout" method="post">
        <input type="submit" value="Deconnecter">
    </form>
<% } %>
<div class="container mt-5">
    <table class="table" border="1">
        <thead>
        <tr>
            <th>Name</th>
            <th>Job</th>
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
</body>
</html>
