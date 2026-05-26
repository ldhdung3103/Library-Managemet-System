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

        response.sendRedirect(FrontendPaths.librarianManageBooks(request));
    }

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

<<<<<<< HEAD
<<<<<<< Updated upstream
=======
            logDao.addLog(
                AuthUtil.getUserId(request),
=======
            logDao.addLog(
                1,
>>>>>>> 41085e156962748b988643765508fbf3d6064184
                "ADD_BOOK",
                "Added book: " + book.getTitle()
            );

<<<<<<< HEAD
>>>>>>> Stashed changes
=======
>>>>>>> 41085e156962748b988643765508fbf3d6064184
        } else if ("delete".equals(action)) {

            int bookId =
                Integer.parseInt(request.getParameter("bookId"));

            boolean success = dao.deleteBook(bookId);
<<<<<<< HEAD
<<<<<<< Updated upstream
            
            if(!success){
=======

            if(success){

                logDao.addLog(
                    1,
                    "DELETE_BOOK",
                    "Deleted book ID: " + bookId
                );

            } else {

>>>>>>> 41085e156962748b988643765508fbf3d6064184
                response.getWriter().println(
                    "<script>alert('Cannot delete: Book is currently borrowed or pending approval.');" +
                    "location='" + request.getContextPath() + "/managebooks';</script>"
=======

            if(success){

                logDao.addLog(
                    AuthUtil.getUserId(request),
                    "DELETE_BOOK",
                    "Deleted book ID: " + bookId
                );

            } else {
                response.sendRedirect(
                        FrontendPaths.librarianManageBooks(request) + "?error=delete_failed"
>>>>>>> Stashed changes
                );
                return;
            }
        }

        response.sendRedirect(FrontendPaths.librarianManageBooks(request));
    }
}