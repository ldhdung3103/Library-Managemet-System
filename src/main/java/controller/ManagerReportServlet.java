<<<<<<< HEAD
package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/managerreport")
public class ManagerReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.sendRedirect(
                request.getContextPath() + "/frontend/manager/report.html"
        );
    }
=======
package controller;

import dao.StatsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/managerreport")
public class ManagerReportServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        StatsDAO dao = new StatsDAO();

        request.setAttribute("stats", dao.getStats());

        request.getRequestDispatcher("manager/report.jsp")
               .forward(request, response);
    }
>>>>>>> 41085e156962748b988643765508fbf3d6064184
}