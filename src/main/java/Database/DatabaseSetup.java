package Database;

public class DatabaseSetup {
    public static void main(String[] args) {
        Database.DatabaseManager db = new Database.DatabaseManager();
        db.connect();

        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS Project (" +
                        "projectID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "projectName TEXT NOT NULL" +
                        ");"
        );

        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS TariffInfo (" +
                        "taricCode INTEGER PRIMARY KEY, " +
                        "countryOfOrigin TEXT NOT NULL, " +
                        "shippingCountry TEXT, " +
                        "deliveryCountry TEXT, " +
                        "value REAL, " +
                        "rate REAL" +
                        ");"
        );

        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS Shipment (" +
                        "shipmentID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "taricCode INTEGER, " +
                        "projectID INTEGER, " +
                        "FOREIGN KEY(taricCode) REFERENCES TariffInfo(taricCode), " +
                        "FOREIGN KEY(projectID) REFERENCES Project(projectID)" +
                        ");"
        );

        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS User (" +
                        "userID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT NOT NULL, " +
                        "password TEXT NOT NULL" +
                        ");"
        );

        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS TradeAgreements (" +
                        "name TEXT PRIMARY KEY, " +
                        "discountRate REAL, " +
                        "usableCountries TEXT" +
                        ");"
        );

        db.disconnect();
        System.out.println("Database setup completed.");
    }
}
