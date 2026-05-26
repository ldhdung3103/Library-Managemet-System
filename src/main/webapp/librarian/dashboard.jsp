<!DOCTYPE html>
<html>
<head>
    <title>Librarian Dashboard</title>
</head>
    <body>

    <h2>Librarian Dashboard</h2>

    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/allborrows">
                View Borrow Records
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/managebooks">
                Manage Books
            </a>
        </li>
        
        <li>
            <a href="${pageContext.request.contextPath}/penalties">
                Manage Penalties
            </a>
        </li>
        
        <li>
            <a href="${pageContext.request.contextPath}/logout">
                Logout
            </a>
        </li>
    </ul>

    </body>
</html>