package controller;

import dao.BorrowDAO;
import filter.AuthFilter;
import model.User;
import utils.AuthUtil;
import utils.FrontendPaths;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/borrow")
public class BorrowServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        User user = AuthUtil.getUser(request);
        if (user == null) {
            AuthFilter.redirectToLogin(request, response);
            return;
        }

        int bookId = Integer.parseInt(request.getParameter("bookId"));

        BorrowDAO dao = new BorrowDAO();
        dao.borrowBook(user.getUserId(), bookId);

        response.sendRedirect(FrontendPaths.studentBooks(request));
    }
}