package dao;

import model.Stats;
import utils.DBConnection;

import java.sql.*;

public class StatsDAO {

    public Stats getStats() {

        Stats s = new Stats();

        try (Connection conn = DBConnection.getConnection()) {

            // TotalUser
            s.setTotalUsers(
                    getCount(conn,
                            "SELECT COUNT(*) FROM users"));

            // TotalBooks
            s.setTotalBooks(
                    getCount(conn,
                            "SELECT COUNT(*) FROM books"));

            // BorrowedBooks
            s.setBorrowedBooks(
                    getCount(conn,
                            "SELECT COUNT(*) FROM borrow_records " +
                            "WHERE status='borrowed'"));

            // PendingRequests
            s.setPendingRequests(
                    getCount(conn,
                            "SELECT COUNT(*) FROM borrow_records " +
                            "WHERE status='pending'"));

            // OverdueBooks
            s.setOverdueBooks(
                    getCount(conn,
                            "SELECT COUNT(*) FROM borrow_records " +
                            "WHERE due_date < CURDATE() " +
                            "AND status='borrowed'"));

            // TotalPenalty
            s.setTotalPenalty(
                    getSum(conn,
                            "SELECT IFNULL(SUM(amount),0) " +
                            "FROM penalties"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    private int getCount(Connection conn, String sql)
            throws SQLException {

        try (
                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    private double getSum(Connection conn, String sql)
            throws SQLException {

        try (
                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {
            return rs.next() ? rs.getDouble(1) : 0;
        }
    }
}