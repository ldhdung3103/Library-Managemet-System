<%@ page import="java.util.List" %>
<%@ page import="model.User" %>

<%
List<User> users = (List<User>) request.getAttribute("users");
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Manage Users</title>
    </head>
    <body>

        <h2>User Management</h2>

        <h3>Add User</h3>

        <form action="${pageContext.request.contextPath}/manageusers"
              method="post">

            <input type="hidden" name="action" value="add">

            Username:
            <input type="text" name="username"><br><br>

            Password:
            <input type="text" name="password"><br><br>

            Full Name:
            <input type="text" name="fullName"><br><br>

            Email:
            <input type="text" name="email"><br><br>

            Role:
            <select name="role">
                <option value="student">Student</option>
                <option value="librarian">Librarian</option>
            </select>

            <br><br>

            <button type="submit">Add User</button>
        </form>

        <hr>

        <table border="1" cellpadding="10">

        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Status</th>
            <th>Action</th>
        </tr>

        <% for(User u : users){ %>
        <tr>

            <td><%= u.getUserId() %></td>
            <td><%= u.getUsername() %></td>
            <td><%= u.getFullName() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getRole() %></td>
            <td><%= u.getStatus() %></td>

            <td>

                <% if("active".equals(u.getStatus())) { %>

                <form action="${pageContext.request.contextPath}/manageusers"
                      method="post"
                      style="display:inline;">

                    <input type="hidden" name="action" value="lock">
                    <input type="hidden" name="userId"
                           value="<%= u.getUserId() %>">

                    <button type="submit">Lock</button>
                </form>

                <% } else { %>

                <form action="${pageContext.request.contextPath}/manageusers"
                      method="post"
                      style="display:inline;">

                    <input type="hidden" name="action" value="unlock">
                    <input type="hidden" name="userId"
                           value="<%= u.getUserId() %>">

                    <button type="submit">Unlock</button>
                </form>

                <% } %>

                <form action="${pageContext.request.contextPath}/manageusers"
                      method="post"
                      style="display:inline;">

                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="userId"
                           value="<%= u.getUserId() %>">

                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
        <% } %>

        </table>
        <br>
        <a href="${pageContext.request.contextPath}/manager/dashboard.jsp">
            Back
        </a>
    </body>
</html>