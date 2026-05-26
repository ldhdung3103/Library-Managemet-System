<<<<<<< HEAD
package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/penalties")
public class ViewPenaltyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.sendRedirect(
                request.getContextPath() + "/frontend/librarian/penalties.html"
        );
    }
=======
package controller;

import dao.PenaltyDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/penalties")
public class ViewPenaltyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        PenaltyDAO dao = new PenaltyDAO();

        request.setAttribute(
            "penalties",
            dao.getAllPenalties()
        );

        request.getRequestDispatcher("librarian/penalties.jsp")
               .forward(request, response);
    }
>>>>>>> 41085e156962748b988643765508fbf3d6064184
}