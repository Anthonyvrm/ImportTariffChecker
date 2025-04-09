package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private Connection connection;

    public void connect() {
        try {

            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/Database/database.db");
            System.out.println("Verbonden met SQLite database.");
        } catch (SQLException e) {
            System.out.println("Fout bij verbinden: " + e.getMessage());
        }
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Verbinding met SQLite database verbroken.");
            }
        } catch (SQLException e) {
            System.out.println("Fout bij verbreken van de verbinding: " + e.getMessage());
        }
    }

    public void executeQuery(String query) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println("Fout bij uitvoeren query: " + e.getMessage());
        }
    }

    public void update(String query) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Fout bij update query: " + e.getMessage());
        }
    }

    public void getInfo(String query) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Voorbeeld: haal een kolom "name" op
                String info = rs.getString("name");
                System.out.println(info);
            }
        } catch (SQLException e) {
            System.out.println("Fout bij ophalen van informatie: " + e.getMessage());
        }
    }
}
