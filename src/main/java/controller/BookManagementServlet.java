package controller;

import dao.BookDAO;
import dao.LogDAO;
import model.Book;
import utils.AuthUtil;
import utils.FrontendPaths;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/managebooks")
public class BookManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.sendRedirect(
            FrontendPaths.librarianManageBooks(request)
        );
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        String action = request.getParameter("action");

        BookDAO dao = new BookDAO();
        LogDAO logDao = new LogDAO();

        if ("add".equals(action)) {

            Book book = new Book();

            book.setTitle(request.getParameter("title"));
            book.setAuthor(request.getParameter("author"));
            book.setCategory(request.getParameter("category"));
            book.setIsbn(request.getParameter("isbn"));

            int quantity =
                Integer.parseInt(request.getParameter("quantity"));

            book.setQuantity(quantity);
            book.setAvailableQuantity(quantity);

            dao.addBook(book);

            logDao.addLog(
                AuthUtil.getUserId(request),
                "ADD_BOOK",
                "Added book: " + book.getTitle()
            );

        } else if ("delete".equals(action)) {

            int bookId =
                Integer.parseInt(request.getParameter("bookId"));

            boolean success = dao.deleteBook(bookId);

            if(success){

                logDao.addLog(
                    AuthUtil.getUserId(request),
                    "DELETE_BOOK",
                    "Deleted book ID: " + bookId
                );

            } else {

                response.sendRedirect(
                    FrontendPaths.librarianManageBooks(request)
                    + "?error=delete_failed"
                );
                return;
            }
        }

        response.sendRedirect(
            FrontendPaths.librarianManageBooks(request)
        );
    }
}