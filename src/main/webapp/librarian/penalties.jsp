<%@ page import="java.util.List" %>

<%
List<String[]> penalties =
(List<String[]>) request.getAttribute("penalties");
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Penalties</title>
    </head>
    <body>

    <h2>Penalty Management</h2>

    <table border="1" cellpadding="10">
        <tr>
            <th>ID</th>
            <th>Student</th>
            <th>Book</th>
            <th>Amount</th>
            <th>Reason</th>
            <th>Status</th>
        </tr>

        <% for(String[] p : penalties){ %>
        <tr>
            <td><%= p[0] %></td>
            <td><%= p[1] %></td>
            <td><%= p[2] %></td>
            <td>$<%= p[3] %></td>
            <td><%= p[4] %></td>
            <td><%= p[5] %></td>
        </tr>
        <% } %>

    </table>

    <br>

    <a href="${pageContext.request.contextPath}/librarian/dashboard.jsp">
        Back
    </a>

    </body>
</html>