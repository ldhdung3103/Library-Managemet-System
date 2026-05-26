package dao;

import model.Stats;
import utils.DBConnection;

import java.sql.*;

public class StatsDAO {

    public Stats getStats() {

        Stats s = new Stats();

        try(Connection conn = DBConnection.getConnection()) {

            s.setTotalUsers(getCount(conn, "SELECT COUNT(*) FROM users"));
            s.setTotalBooks(getCount(conn, "SELECT COUNT(*) FROM books"));

            s.setBorrowedBooks(getCount(conn,
                "SELECT COUNT(*) FROM borrow_records WHERE status='borrowed'"));

            s.setPendingRequests(getCount(conn,
                "SELECT COUNT(*) FROM borrow_records WHERE status='pending'"));

            s.setOverdueBooks(getCount(conn,
                "SELECT COUNT(*) FROM borrow_records " +
                "WHERE due_date < CURDATE() AND status='borrowed'"));

            s.setTotalPenalty(getSum(conn,
                "SELECT IFNULL(SUM(amount),0) FROM penalties"));

        } catch(Exception e){
            e.printStackTrace();
        }

        return s;
    }

    private int getCount(Connection conn, String sql) throws Exception {
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    private double getSum(Connection conn, String sql) throws Exception {
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getDouble(1);
    }
}