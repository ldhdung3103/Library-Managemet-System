<%@ page import="java.util.*" %>

<%
List<String[]> logs =
(List<String[]>) request.getAttribute("logs");
%>

<!DOCTYPE html>
<html>
    <head>
        <title>System Logs</title>
    </head>
    <body>
        <h2>Activity Logs</h2>

        <table border="1" cellpadding="10">
            <tr>
                <th>User</th>
                <th>Action</th>
                <th>Details</th>
                <th>Time</th>
            </tr>

            <% for(String[] log : logs){ %>
            <tr>
                <td><%= log[0] %></td>
                <td><%= log[1] %></td>
                <td><%= log[2] %></td>
                <td><%= log[3] %></td>
            </tr>
            <% } %>
        </table>

        <br>

        <a href="${pageContext.request.contextPath}/managerdashboard">
            Back
        </a>

    </body>
</html>