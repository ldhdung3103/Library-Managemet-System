<%@ page import="model.Stats" %>

<%
Stats stats = (Stats) request.getAttribute("stats");

if(stats == null){
    response.sendRedirect(request.getContextPath() + "/managerdashboard");
    return;
}
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manager Dashboard</title>
    </head>
    <body>
        <h1>Manager Dashboard</h1>
        <hr>
        <h3>System Statistics</h3>

        <p>Total Users: <%= stats.getTotalUsers() %></p>
        <p>Total Books: <%= stats.getTotalBooks() %></p>
        <p>Borrowed Books: <%= stats.getBorrowedBooks() %></p>
        <p>Pending Requests: <%= stats.getPendingRequests() %></p>

        <hr>

        <a href="${pageContext.request.contextPath}/manageusers">
            Manage Users
        </a>

        <br><br>

        <a href="${pageContext.request.contextPath}/viewlogs">
            View Activity Logs
        </a>
            
        <br><br>
        
        <a href="${pageContext.request.contextPath}/managerreport">
            View Report
        </a>
            
        <br><br>
        
        <a href="${pageContext.request.contextPath}/logout">
            Logout
        </a>
    </body>
</html>