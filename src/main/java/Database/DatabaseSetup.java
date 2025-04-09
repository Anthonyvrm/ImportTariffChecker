package Classes;

public class DatabaseSetup {
    public static void main(String[] args) {
        Database.DatabaseManager db = new Database.DatabaseManager();
        db.connect();

        // Tabel voor Project (projectID en projectName)
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

        // Tabel voor Shipment. Hier slaan we een referentie op naar TariffInfo
        // en (optioneel) een referentie naar een Project
        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS Shipment (" +
                        "shipmentID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tariffCode INTEGER, " +
                        "projectID INTEGER, " +
                        "FOREIGN KEY(tariffCode) REFERENCES TariffInfo(tariffCode), " +
                        "FOREIGN KEY(projectID) REFERENCES Project(projectID)" +
                        ");"
        );

        // Tabel voor User
        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS User (" +
                        "userID INTEGER PRIMARY KEY, " +
                        "name TEXT NOT NULL, " +
                        "password TEXT NOT NULL" +
                        ");"
        );

        // Tabel voor TradeAgreements
        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS TradeAgreements (" +
                        "name TEXT PRIMARY KEY, " +
                        "discountRate REAL" +
                        ");"
        );

        // Tabel voor de landen die gebruikt mogen worden per TradeAgreement
        db.executeQuery(
                "CREATE TABLE IF NOT EXISTS TradeAgreementsCountries (" +
                        "tradeAgreementName TEXT, " +
                        "country TEXT, " +
                        "FOREIGN KEY(tradeAgreementName) REFERENCES TradeAgreements(name)" +
                        ");"
        );

        db.disconnect();
        System.out.println("Database setup voltooid.");
    }
}
