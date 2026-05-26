<%@ page import="model.Stats" %>

<%
Stats stats = (Stats) request.getAttribute("stats");
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Manager Report</title>
    </head>
    <body>

    <h2>Library Report</h2>

    <table border="1" cellpadding="10">
        <tr>
            <td>Total Users</td>
            <td><%= stats.getTotalUsers() %></td>
        </tr>
        <tr>
            <td>Total Books</td>
            <td><%= stats.getTotalBooks() %></td>
        </tr>
        <tr>
            <td>Borrowed Books</td>
            <td><%= stats.getBorrowedBooks() %></td>
        </tr>
        <tr>
            <td>Pending Requests</td>
            <td><%= stats.getPendingRequests() %></td>
        </tr>
        <tr>
            <td>Overdue Books</td>
            <td><%= stats.getOverdueBooks() %></td>
        </tr>
        <tr>
            <td>Total Penalties</td>
            <td>$<%= stats.getTotalPenalty() %></td>
        </tr>
    </table>

    <br>

    <a href="${pageContext.request.contextPath}/manager/dashboard.jsp">
        Back
    </a>

    </body>
</html>