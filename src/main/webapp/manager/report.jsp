<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="model.Stats" %>

<%
    Stats stats = (Stats) request.getAttribute("stats");
%>

<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <title>Reports</title>

  <script src="../js/base-path.js"></script>
  <link rel="stylesheet" href="../css/styles.css">
</head>

<body data-page="manager-report">

  <div class="container">

    <div class="page-panel">

      <div class="header-row">
        <h2>📈 Library Report</h2>
        <div id="userBar"></div>
      </div>

      <div class="card-grid" id="reportGrid" style="margin-top:24px;">

        <div class="card">
          <h3>Total Users</h3>
          <p><%= stats.getTotalUsers() %></p>
        </div>

        <div class="card">
          <h3>Total Books</h3>
          <p><%= stats.getTotalBooks() %></p>
        </div>

        <div class="card">
          <h3>Borrowed Books</h3>
          <p><%= stats.getBorrowedBooks() %></p>
        </div>

        <div class="card">
          <h3>Pending Requests</h3>
          <p><%= stats.getPendingRequests() %></p>
        </div>

        <div class="card">
          <h3>Overdue Books</h3>
          <p><%= stats.getOverdueBooks() %></p>
        </div>

        <div class="card">
          <h3>Total Penalties</h3>
          <p>$<%= stats.getTotalPenalty() %></p>
        </div>

      </div>

      <p style="margin-top:16px;">
        <a class="secondary-btn"
           href="${pageContext.request.contextPath}/manager/dashboard.jsp">
          ← Back
        </a>
      </p>

    </div>

  </div>

<script src="../js/app.js" defer></script>

</body>
</html>