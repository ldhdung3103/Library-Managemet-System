package controller;

import dao.BorrowDAO;
import dao.LogDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import utils.AuthUtil;
import utils.FrontendPaths;

import java.io.IOException;

@WebServlet("/reject")
public class RejectBorrowServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        int borrowId =
            Integer.parseInt(request.getParameter("borrowId"));

        BorrowDAO dao = new BorrowDAO();
        dao.rejectBorrow(borrowId);

<<<<<<< HEAD
<<<<<<< Updated upstream
=======
        new LogDAO().addLog(
            1,
            "REJECT_BORROW",
            "Rejected borrow ID " + borrowId
        );

>>>>>>> 41085e156962748b988643765508fbf3d6064184
        response.sendRedirect(
            request.getContextPath() + "/allborrows"
        );
=======
        new LogDAO().addLog(
            AuthUtil.getUserId(request),
            "REJECT_BORROW",
            "Rejected borrow ID " + borrowId
        );

        response.sendRedirect(FrontendPaths.librarianAllBorrows(request));
>>>>>>> Stashed changes
    }
}