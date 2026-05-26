package dao;

import utils.DBConnection;
import java.sql.*;
import java.util.*;

public class LogDAO {

    public List<String[]> getAllLogs() {

        List<String[]> logs = new ArrayList<>();

        String sql =
            "SELECT u.username, a.action, a.details, a.log_time " +
            "FROM activity_logs a " +
            "LEFT JOIN users u ON a.user_id = u.user_id " +
            "ORDER BY a.log_time DESC";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {

                logs.add(new String[]{
                    rs.getString("username"),
                    rs.getString("action"),
                    rs.getString("details"),
                    rs.getString("log_time")
                });
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return logs;
    }

    public void addLog(int userId, String action, String details) {

        String sql =
            "INSERT INTO activity_logs(user_id, action, details) VALUES(?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, action);
            ps.setString(3, details);

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}