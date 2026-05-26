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
}