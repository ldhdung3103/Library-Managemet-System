package controller;

import com.google.gson.Gson;
import dao.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;
import utils.AuthUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet("/api/*")
public class ApiServlet extends HttpServlet {

    private static final Gson GSON = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        User user = AuthUtil.getUser(request);
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }

        if ("/session".equals(pathInfo)) {
            if (user == null) {
                writeJson(response, 401, Map.of("authenticated", false));
                return;
            }
            writeJson(response, Map.of(
                    "authenticated", true,
                    "userId", user.getUserId(),
                    "username", user.getUsername(),
                    "fullName", user.getFullName() != null ? user.getFullName() : user.getUsername(),
                    "role", user.getRole()
            ));
            return;
        }

        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        switch (pathInfo) {
            case "/books" -> {
                String role = user.getRole();
                if (!"student".equals(role) && !"librarian".equals(role)) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
                writeJson(response, new BookDAO().getAllBooks());
            }
            case "/mybooks" -> {
                if (!"student".equals(user.getRole())) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
                writeJson(response, new BorrowDAO().getBorrowedBooks(user.getUserId()));
            }
            case "/allborrows" -> writeJson(response, new BorrowDAO().getAllBorrowRecords());
            case "/stats", "/report" -> writeJson(response, new StatsDAO().getStats());
            case "/users" -> {
                List<Map<String, Object>> safe = new ArrayList<>();
                for (User u : new UserDAO().getAllUsers()) {
                    safe.add(Map.of(
                            "userId", u.getUserId(),
                            "username", u.getUsername(),
                            "fullName", u.getFullName() != null ? u.getFullName() : "",
                            "email", u.getEmail() != null ? u.getEmail() : "",
                            "role", u.getRole(),
                            "status", u.getStatus() != null ? u.getStatus() : ""
                    ));
                }
                writeJson(response, safe);
            }
            case "/logs" -> {
                List<Map<String, String>> logs = new ArrayList<>();
                for (String[] row : new LogDAO().getAllLogs()) {
                    logs.add(Map.of(
                            "username", row[0] != null ? row[0] : "",
                            "action", row[1] != null ? row[1] : "",
                            "details", row[2] != null ? row[2] : "",
                            "logTime", row[3] != null ? row[3] : ""
                    ));
                }
                writeJson(response, logs);
            }
            case "/penalties" -> {
                List<Map<String, String>> list = new ArrayList<>();
                for (String[] row : new PenaltyDAO().getAllPenalties()) {
                    list.add(Map.of(
                            "penaltyId", row[0],
                            "username", row[1],
                            "bookTitle", row[2],
                            "amount", row[3],
                            "reason", row[4] != null ? row[4] : "",
                            "status", row[5] != null ? row[5] : ""
                    ));
                }
                writeJson(response, list);
            }
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void writeJson(HttpServletResponse response, Object data) throws IOException {
        writeJson(response, 200, data);
    }

    private void writeJson(HttpServletResponse response, int status, Object data)
            throws IOException {
        response.setStatus(status);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(GSON.toJson(data));
    }
}
