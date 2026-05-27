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

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        int borrowId =
            Integer.parseInt(request.getParameter("borrowId"));

        BorrowDAO dao = new BorrowDAO();
        dao.rejectBorrow(borrowId);

        new LogDAO().addLog(
            AuthUtil.getUserId(request),
            "REJECT_BORROW",
            "Rejected borrow ID " + borrowId
        );

        response.sendRedirect(
            FrontendPaths.librarianAllBorrows(request)
        );
    }
}