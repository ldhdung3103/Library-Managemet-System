<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, model.Book" %>

<html>
    <head>
        <title>Manage Books</title>
    </head>
    <body>
    <h2>Book Management</h2>
    <h3>Add New Book</h3>
    <form action="${pageContext.request.contextPath}/managebooks"
          method="post">
        <input type="hidden" name="action" value="add">
        <input type="text" name="title" placeholder="Title" required>
        <input type="text" name="author" placeholder="Author" required>
        <input type="text" name="category" placeholder="Category">
        <input type="text" name="isbn" placeholder="ISBN">
        <input type="number" name="quantity" placeholder="Quantity" required>
        <button type="submit">Add Book</button>
    </form>
    <hr>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Available</th>
            <th>Action</th>
        </tr>
    <%
    List<Book> books = (List<Book>) request.getAttribute("books");

    for(Book b : books){
    %>
        <tr>
            <td><%= b.getBookId() %></td>
            <td><%= b.getTitle() %></td>
            <td><%= b.getAuthor() %></td>
            <td><%= b.getCategory() %></td>
            <td><%= b.getAvailableQuantity() %></td>

            <td>
                <form action="${pageContext.request.contextPath}/managebooks"
                      method="post">

                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="bookId"
                           value="<%= b.getBookId() %>">

                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    <%
    }
    %>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/librarian/dashboard.jsp">
        Back
    </a>
    </body>
</html>