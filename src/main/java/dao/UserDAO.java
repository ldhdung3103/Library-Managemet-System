package dao;

import model.User;
import utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // LOGIN
    public User login(String username, String password) {

        User user = null;

        try(Connection conn = DBConnection.getConnection()) {

            String sql =
                "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

<<<<<<< HEAD
<<<<<<< Updated upstream
            if(rs.next()){
=======
            if(rs.next()) {
                String status = rs.getString("status");
                if (status != null && !"active".equalsIgnoreCase(status)) {
                    return null;
                }

>>>>>>> Stashed changes
=======
            if(rs.next()) {

>>>>>>> 41085e156962748b988643765508fbf3d6064184
                user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
                user.setStatus(status);
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
>>>>>>> Stashed changes
=======
                user.setStatus(rs.getString("status"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
>>>>>>> 41085e156962748b988643765508fbf3d6064184
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }

    // REGISTER
    public boolean register(User user) {

        String sql =
            "INSERT INTO users(username,password,full_name,email,role,status) " +
            "VALUES(?,?,?,?,?,'active')";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getRole());

            return ps.executeUpdate() > 0;

        } catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    // GET ALL USERS
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {

                User u = new User();

                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setFullName(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                u.setStatus(rs.getString("status"));

                users.add(u);
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return users;
    }

    // ADD USER
    public void addUser(User user) {

        String sql =
            "INSERT INTO users(username,password,full_name,email,role,status) " +
            "VALUES(?,?,?,?,?,'active')";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getRole());

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // LOCK / UNLOCK
    public void updateStatus(int userId, String status) {

        String sql =
            "UPDATE users SET status=? WHERE user_id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, userId);

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // DELETE USER
    public void deleteUser(int userId) {

        String sql =
            "DELETE FROM users WHERE user_id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}