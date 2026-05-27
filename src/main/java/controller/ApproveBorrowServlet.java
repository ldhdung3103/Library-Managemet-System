package controller;

import dao.BorrowDAO;
import dao.LogDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import utils.AuthUtil;
import utils.FrontendPaths;

import java.io.IOException;

@WebServlet("/approve")
public class ApproveBorrowServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        int borrowId =
            Integer.parseInt(request.getParameter("borrowId"));

        BorrowDAO dao = new BorrowDAO();

        boolean success = dao.approveBorrow(borrowId);

        if(success){

            new LogDAO().addLog(
                AuthUtil.getUserId(request),
                "APPROVE_BORROW",
                "Approved borrow ID " + borrowId
            );

            response.sendRedirect(
                FrontendPaths.librarianAllBorrows(request)
            );

        } else {

            response.sendRedirect(
                FrontendPaths.librarianAllBorrows(request)
                + "?error=no_stock"
            );
        }
    }
}