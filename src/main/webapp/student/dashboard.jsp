<%@ page import="model.User" %>
<%
    User user = (User) session.getAttribute("user");

    if(user == null){
        response.sendRedirect("../login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard</title>
</head>
<body>

<h1>Welcome, <%= user.getUsername() %></h1>

<h3>Student Dashboard</h3>

<ul>
    <li><a href="${pageContext.request.contextPath}/books">Browse Books</a></li>
    <li><a href="${pageContext.request.contextPath}/mybooks">My Borrowed Books</a></li>
    <li><a href="../logout">Logout</a></li>
</ul>

</body>
</html>