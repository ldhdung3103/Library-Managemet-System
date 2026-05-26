package controller;

import dao.BorrowDAO;
import dao.LogDAO;
import dao.PenaltyDAO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/return")
public class ReturnServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        int borrowId =
                Integer.parseInt(request.getParameter("borrowId"));

        BorrowDAO borrowDAO = new BorrowDAO();

        boolean success = borrowDAO.returnBook(borrowId);

        if(success){

            // Check overdue -> create penalty
            PenaltyDAO penaltyDAO = new PenaltyDAO();
            penaltyDAO.createPenaltyIfLate(borrowId);

            // Log activity
            new LogDAO().addLog(
                    1,
                    "RETURN_BOOK",
                    "Returned borrow ID " + borrowId
            );

            response.sendRedirect(
                    request.getContextPath() + "/allborrows"
            );

        } else {

            response.getWriter().println(
                    "<script>alert('Return failed');" +
                    "location='" + request.getContextPath() + "/allborrows';</script>"
            );
        }
    }
}