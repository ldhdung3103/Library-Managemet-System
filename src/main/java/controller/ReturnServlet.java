package controller;

import dao.BorrowDAO;
import dao.LogDAO;
import dao.PenaltyDAO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import utils.AuthUtil;
import utils.FrontendPaths;

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

<<<<<<< HEAD
<<<<<<< Updated upstream
        response.sendRedirect(
            request.getContextPath() + "/allborrows"
        );
=======
=======
>>>>>>> 41085e156962748b988643765508fbf3d6064184
        boolean success = borrowDAO.returnBook(borrowId);

        if(success){

<<<<<<< HEAD
            PenaltyDAO penaltyDAO = new PenaltyDAO();
            penaltyDAO.createPenaltyIfLate(borrowId);

            new LogDAO().addLog(
                    AuthUtil.getUserId(request),
=======
            // Check overdue -> create penalty
            PenaltyDAO penaltyDAO = new PenaltyDAO();
            penaltyDAO.createPenaltyIfLate(borrowId);

            // Log activity
            new LogDAO().addLog(
                    1,
>>>>>>> 41085e156962748b988643765508fbf3d6064184
                    "RETURN_BOOK",
                    "Returned borrow ID " + borrowId
            );

<<<<<<< HEAD
            response.sendRedirect(FrontendPaths.librarianAllBorrows(request));

        } else {
            response.sendRedirect(
                    FrontendPaths.librarianAllBorrows(request) + "?error=return_failed"
            );
        }
>>>>>>> Stashed changes
=======
            response.sendRedirect(
                    request.getContextPath() + "/allborrows"
            );

        } else {

            response.getWriter().println(
                    "<script>alert('Return failed');" +
                    "location='" + request.getContextPath() + "/allborrows';</script>"
            );
        }
>>>>>>> 41085e156962748b988643765508fbf3d6064184
    }
}