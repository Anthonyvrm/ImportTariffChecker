package Classes;

import Database.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private int userID;
    private String name;
    private String password;


    public User(String name, String password) {
        this.userID = 0;
        this.name = name;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User login(String username, String password) {
        DatabaseManager db = new DatabaseManager();
        db.connect();
        String query = "SELECT * FROM User WHERE name = ? AND password = ?;";
        try (PreparedStatement pstmt = db.getConnection().prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(rs.getString("name"), rs.getString("password"));
                    user.userID = rs.getInt("userID");
                    System.out.println("Login successful!");
                    return user;
                } else {
                    System.out.println("Login failed. Please check your credentials.");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
            return null;
        } finally {
            db.disconnect();
        }
    }

    public static boolean register(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password must be provided for registration.");
            return false;
        }
        String query = "INSERT INTO User (name, password) VALUES (?, ?);";
        DatabaseManager db = new DatabaseManager();
        db.connect();
        try (PreparedStatement pstmt = db.getConnection().prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            System.out.println("User " + username + " registered successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error during registration: " + e.getMessage());
            return false;
        } finally {
            db.disconnect();
        }
    }


    public void logout() {

        System.out.println("User " + name + " logged out successfully.");
    }
}