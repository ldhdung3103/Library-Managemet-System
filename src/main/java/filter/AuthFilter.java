package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final Set<String> PUBLIC_EXACT = Set.of(
            "/",
            "/index.jsp",
            "/login.jsp",
            "/frontend/index.html",
            "/frontend/login.html"
    );

    private static final Map<String, String> PATH_ROLES = Map.ofEntries(
            Map.entry("/frontend/student", "student"),
            Map.entry("/books", "student"),
            Map.entry("/borrow", "student"),
            Map.entry("/mybooks", "student"),
            Map.entry("/api/mybooks", "student"),

            Map.entry("/frontend/librarian", "librarian"),
            Map.entry("/allborrows", "librarian"),
            Map.entry("/managebooks", "librarian"),
            Map.entry("/approve", "librarian"),
            Map.entry("/reject", "librarian"),
            Map.entry("/penalties", "librarian"),
            Map.entry("/return", "librarian"),
            Map.entry("/api/allborrows", "librarian"),
            Map.entry("/api/penalties", "librarian"),

            Map.entry("/frontend/manager", "manager"),
            Map.entry("/student", "student"),
            Map.entry("/librarian", "librarian"),
            Map.entry("/manager", "manager"),
            Map.entry("/manageusers", "manager"),
            Map.entry("/viewlogs", "manager"),
            Map.entry("/managerreport", "manager"),
            Map.entry("/managerdashboard", "manager"),
            Map.entry("/api/stats", "manager"),
            Map.entry("/api/users", "manager"),
            Map.entry("/api/logs", "manager"),
            Map.entry("/api/report", "manager")
    );

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = normalizePath(request);
        String method = request.getMethod();

        if (isPublic(path, method)) {
            chain.doFilter(req, res);
            return;
        }

        if (path.equals("/api/session")) {
            chain.doFilter(req, res);
            return;
        }

        HttpSession session = request.getSession(false);
        User user = session != null
                ? (User) session.getAttribute("user")
                : null;

        if (user == null) {
            redirectToLogin(request, response);
            return;
        }

        String requiredRole = requiredRoleForPath(path);
        if (requiredRole != null && !requiredRole.equals(user.getRole())) {
            redirectToRoleHome(response, request.getContextPath(), user.getRole());
            return;
        }

        chain.doFilter(req, res);
    }

    private static String normalizePath(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String ctx = request.getContextPath();
        String path = uri.substring(ctx.length());
        return path.isEmpty() ? "/" : path;
    }

    private static boolean isPublic(String path, String method) {
        if (path.startsWith("/frontend/css/")
                || path.startsWith("/frontend/js/")) {
            return true;
        }
        if (PUBLIC_EXACT.contains(path)) {
            return true;
        }
        if ("/login".equals(path) && "POST".equals(method)) {
            return true;
        }
        return "/logout".equals(path);
    }

    private static String requiredRoleForPath(String path) {
        for (Map.Entry<String, String> entry : PATH_ROLES.entrySet()) {
            String prefix = entry.getKey();
            if (path.equals(prefix) || path.startsWith(prefix + "/")) {
                return entry.getValue();
            }
        }
        if (path.startsWith("/api/")) {
            return PATH_ROLES.get(path);
        }
        return null;
    }

    public static void redirectToLogin(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        String ctx = request.getContextPath();
        response.sendRedirect(ctx + "/frontend/login.html");
    }

    public static void redirectToRoleHome(HttpServletResponse response,
                                   String contextPath,
                                   String role) throws IOException {
        String home = switch (role) {
            case "student" -> contextPath + "/frontend/student/dashboard.html";
            case "librarian" -> contextPath + "/frontend/librarian/dashboard.html";
            case "manager" -> contextPath + "/frontend/manager/dashboard.html";
            default -> contextPath + "/frontend/login.html";
        };
        response.sendRedirect(home);
    }
}
