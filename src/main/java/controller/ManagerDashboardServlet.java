<<<<<<< HEAD
package controller;

import utils.FrontendPaths;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/managerdashboard")
public class ManagerDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.sendRedirect(FrontendPaths.managerDashboard(request));
    }
=======
package controller;

import dao.StatsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/managerdashboard")
public class ManagerDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        StatsDAO dao = new StatsDAO();

        request.setAttribute("stats", dao.getStats());

        request.getRequestDispatcher("/manager/dashboard.jsp")
               .forward(request, response);
    }
>>>>>>> 41085e156962748b988643765508fbf3d6064184
}