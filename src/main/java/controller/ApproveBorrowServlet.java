package controller;

import dao.BorrowDAO;
import dao.LogDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/approve")
public class ApproveBorrowServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        int borrowId =
            Integer.parseInt(request.getParameter("borrowId"));

        BorrowDAO dao = new BorrowDAO();

        boolean success = dao.approveBorrow(borrowId);

        if(success){

            new LogDAO().addLog(
                1,
                "APPROVE_BORROW",
                "Approved borrow ID " + borrowId
            );

            response.sendRedirect(
                request.getContextPath() + "/allborrows"
            );

        } else {
            response.getWriter().println("No available books.");
        }
    }
}