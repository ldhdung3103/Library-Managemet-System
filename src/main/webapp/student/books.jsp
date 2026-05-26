<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>

<%
List<Book> books = (List<Book>) request.getAttribute("books");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Browse Books</title>
</head>
    <body>

        <h2>Available Books</h2>

        <table border="1" cellpadding="10">
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>Category</th>
                <th>Available</th>
                <th>Borrow</th>
            </tr>

            <% for(Book b : books){ %>
            <tr>
                <td><%= b.getTitle() %></td>
                <td><%= b.getAuthor() %></td>
                <td><%= b.getCategory() %></td>
                <td><%= b.getAvailableQuantity() %></td>
                <td>
                    <form action="${pageContext.request.contextPath}/borrow" method="post">
                        <input type="hidden" name="bookId" value="<%= b.getBookId() %>">
                        <button type="submit">Borrow</button>
                    </form>
                </td>
            </tr>
            <% } %>

        </table>

        <br>
        <a href="${pageContext.request.contextPath}/student/dashboard.jsp">Back</a>

    </body>
</html>