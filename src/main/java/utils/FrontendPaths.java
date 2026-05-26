package utils;

import jakarta.servlet.http.HttpServletRequest;

public final class FrontendPaths {

    private FrontendPaths() {}

    public static String login(HttpServletRequest request) {
        return request.getContextPath() + "/frontend/login.html";
    }

    public static String studentDashboard(HttpServletRequest request) {
        return request.getContextPath() + "/frontend/student/dashboard.html";
    }

    public static String studentBooks(HttpServletRequest request) {
        return request.getContextPath() + "/frontend/student/books.html";
    }

    public static String studentMyBooks(HttpServletRequest request) {
        return request.getContextPath() + "/frontend/student/mybooks.html";
    }

    public static String librarianDashboard(HttpServletRequest request) {
        return request.getContextPath() + "/frontend/librarian/dashboard.html";
    }

    public static String librarianAllBorrows(HttpServletRequest request) {
        return request.getContextPath() + "/frontend/librarian/allborrows.html";
    }

    public static String librarianManageBooks(HttpServletRequest request) {
        return request.getContextPath() + "/frontend/librarian/managebooks.html";
    }

    public static String managerDashboard(HttpServletRequest request) {
        return request.getContextPath() + "/frontend/manager/dashboard.html";
    }

    public static String managerManageUsers(HttpServletRequest request) {
        return request.getContextPath() + "/frontend/manager/manageusers.html";
    }
}
