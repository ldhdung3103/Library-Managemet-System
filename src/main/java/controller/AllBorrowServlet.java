package controller;

import utils.FrontendPaths;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/allborrows")
public class AllBorrowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.sendRedirect(FrontendPaths.librarianAllBorrows(request));
    }
}