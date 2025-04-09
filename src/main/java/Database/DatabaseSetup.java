package Database;

public class DatabaseSetup {
    public static void main(String[] args) {
        Database.DatabaseManager db = new Database.DatabaseManager();
        db.connect();


        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS Project (" +
                        "projectID INTEGER PRIMARY KEY, " +
                        "projectName TEXT NOT NULL" +
                        ");"
        );

        // Tabel voor TariffInfo (met alle velden)
        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS TariffInfo (" +
                        "tariffCode INTEGER PRIMARY KEY, " +
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
                        "tariffCode INTEGER, " +
                        "projectID INTEGER, " +
                        "FOREIGN KEY(tariffCode) REFERENCES TariffInfo(tariffCode), " +
                        "FOREIGN KEY(projectID) REFERENCES Project(projectID)" +
                        ");"
        );


        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS User (" +
                        "userID INTEGER PRIMARY KEY, " +
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
        System.out.println("Database setup voltooid.");
    }
}
