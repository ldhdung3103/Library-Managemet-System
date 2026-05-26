package dao;

import utils.DBConnection;
import java.sql.*;
import java.util.*;

public class PenaltyDAO {

    public void createPenaltyIfLate(int borrowId) {

        String checkSql =
            "SELECT DATEDIFF(CURDATE(), due_date) AS late_days " +
            "FROM borrow_records WHERE borrow_id=?";

        String insertSql =
            "INSERT INTO penalties(borrow_id, amount, reason, status) " +
            "VALUES(?,?,?, 'unpaid')";

        try(Connection conn = DBConnection.getConnection()) {

            PreparedStatement check = conn.prepareStatement(checkSql);
            check.setInt(1, borrowId);

            ResultSet rs = check.executeQuery();

            if(rs.next()) {

                int lateDays = rs.getInt("late_days");

                if(lateDays > 0) {

                    double fine = lateDays * 5.0;

                    PreparedStatement insert =
                        conn.prepareStatement(insertSql);

                    insert.setInt(1, borrowId);
                    insert.setDouble(2, fine);
                    insert.setString(3,
                        "Late return (" + lateDays + " days)");

                    insert.executeUpdate();
                }
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<String[]> getAllPenalties() {

        List<String[]> list = new ArrayList<>();

        String sql =
            "SELECT p.penalty_id, u.username, b.title, p.amount, p.reason, p.status " +
            "FROM penalties p " +
            "JOIN borrow_records br ON p.borrow_id = br.borrow_id " +
            "JOIN users u ON br.user_id = u.user_id " +
            "JOIN books b ON br.book_id = b.book_id";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {

                list.add(new String[]{
                    rs.getString("penalty_id"),
                    rs.getString("username"),
                    rs.getString("title"),
                    rs.getString("amount"),
                    rs.getString("reason"),
                    rs.getString("status")
                });
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}