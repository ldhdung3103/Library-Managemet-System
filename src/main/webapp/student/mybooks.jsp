<%@ page import="java.util.List" %>
<%@ page import="model.BorrowRecord" %>

<%
List<BorrowRecord> records =
(List<BorrowRecord>) request.getAttribute("records");
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Borrowed Books</title>
</head>
<body>

<h2>My Borrowed Books</h2>

<table border="1" cellpadding="10">
    <tr>
        <th>Title</th>
        <th>Borrow Date</th>
        <th>Due Date</th>
        <th>Status</th>
    </tr>

<% for(BorrowRecord r : records){ %>
<tr>
    <td><%= r.getTitle() %></td>
    <td><%= r.getBorrowDate() %></td>
    <td><%= r.getDueDate() %></td>
    <td><%= r.getStatus() %></td>
</tr>
<% } %>

</table>

<br>

<a href="${pageContext.request.contextPath}/student/dashboard.jsp">
    Back
</a>

</body>
</html>