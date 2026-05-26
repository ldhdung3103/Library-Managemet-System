package controller;

import filter.AuthFilter;
import model.User;
import utils.AuthUtil;
import utils.FrontendPaths;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/mybooks")
public class MyBooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        User user = AuthUtil.getUser(request);
        if (user == null) {
            AuthFilter.redirectToLogin(request, response);
            return;
        }

        response.sendRedirect(FrontendPaths.studentMyBooks(request));
    }
}