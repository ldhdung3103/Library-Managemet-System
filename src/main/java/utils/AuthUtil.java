package utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.User;

public final class AuthUtil {

    private AuthUtil() {}

    public static User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (User) session.getAttribute("user");
    }

    public static int getUserId(HttpServletRequest request) {
        User user = getUser(request);
        return user != null ? user.getUserId() : 0;
    }
}
