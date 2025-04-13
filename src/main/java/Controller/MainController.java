package Controller;
import Classes.*;
import Database.DatabaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainController {

    @FXML
    private Label totalTariffCostLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button logoutButton;


    @FXML
    private TextField projectNameField;
    @FXML
    private Button createProjectButton;
    @FXML
    private Button updateProjectButton;
    @FXML
    private ListView<Project> projectListView;

    @FXML
    private TextField taricCodeField;
    @FXML
    private TextField countryOriginField;
    @FXML
    private TextField shippingCountryField;
    @FXML
    private TextField deliveryCountryField;
    @FXML
    private TextField valueField;
    @FXML
    private TextField rateField;
    @FXML
    private Button lookupTariffButton;

    @FXML
    private TableView<TariffInfo> tariffTableView;
    @FXML
    private TableColumn<TariffInfo, String> taricCodeColumn;
    @FXML
    private TableColumn<TariffInfo, String> countryOriginColumn;
    @FXML
    private TableColumn<TariffInfo, Number> rateColumn;
    @FXML
    private TableColumn<TariffInfo, String> calculatedTariffColumn;


    @FXML
    private TextField shipmentNameField;
    @FXML
    private ComboBox<TariffInfo> tariffComboBox;
    @FXML
    private Button addTariffToShipmentButton;
    @FXML
    private ListView<TariffInfo> currentShipmentTariffListView;
    @FXML
    private Button createShipmentButton;
    @FXML
    private TableView<Shipment> shipmentTableView;
    @FXML
    private TableColumn<Shipment, String> shipmentNameColumn;
    @FXML
    private TableColumn<Shipment, String> taricCodeShipmentColumn;

    private ObservableList<Project> projects = FXCollections.observableArrayList();
    private ObservableList<TariffInfo> tariffs = FXCollections.observableArrayList();
    private ObservableList<Shipment> shipments = FXCollections.observableArrayList();


    private ObservableList<TariffInfo> tempShipmentTariffs = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        taricCodeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getTaricCode()))
        );
        countryOriginColumn.setCellValueFactory(new PropertyValueFactory<>("countryOfOrigin"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));
        calculatedTariffColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCalculatedTariff()))
        );
        tariffTableView.setItems(tariffs);


        projectListView.setItems(projects);


        shipmentNameColumn.setCellValueFactory(new PropertyValueFactory<>("shipmentName"));
        taricCodeShipmentColumn.setCellValueFactory(cellData -> {

            if (!cellData.getValue().getTariffInfos().isEmpty()) {
                return new SimpleStringProperty(
                        String.valueOf(cellData.getValue().getTariffInfos().get(0).getTaricCode())
                );
            } else {
                return new SimpleStringProperty("N/A");
            }
        });
        shipmentTableView.setItems(shipments);

        tariffComboBox.setItems(tariffs);
        currentShipmentTariffListView.setItems(tempShipmentTariffs);


        loginButton.setOnAction(event -> handleLogin());
        registerButton.setOnAction(event -> handleRegister());
        logoutButton.setOnAction(event -> handleLogout());
        createProjectButton.setOnAction(event -> handleCreateProject());
        updateProjectButton.setOnAction(event -> handleUpdateProject());
        lookupTariffButton.setOnAction(event -> handleLookupTariff());

        addTariffToShipmentButton.setOnAction(event -> handleAddTariffToShipment());
        createShipmentButton.setOnAction(event -> handleCreateShipment());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Please enter both username and password.");
            return;
        }

        User user = User.login(username, password);
        if (user != null) {

            System.out.println("Login successful!");

        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
    }


    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean success = User.register(username, password);
        if (success) {
            System.out.println("Registration successful!");

        } else {
            System.out.println("Registration failed.");
        }
    }


    private void handleLogout() {

        usernameField.clear();
        passwordField.clear();
        System.out.println("Logged out successfully.");
    }


    private void handleCreateProject() {
        String projectName = projectNameField.getText();
        if (!projectName.isEmpty()) {

            Project project = new Project(projectName);
            projects.add(project);
            projectListView.setItems(projects);
            System.out.println("Created project: " + projectName);
        } else {
            System.out.println("Project name cannot be empty.");
        }
    }


    private void handleUpdateProject() {
        Project selectedProject = projectListView.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            String newProjectName = projectNameField.getText();
            selectedProject.setProjectName(newProjectName);
            projectListView.refresh();
            System.out.println("Updated project: " + newProjectName);
        } else {
            System.out.println("Select a project to update.");
        }
    }

    private void updateTotalTariffCost() {
        Project selectedProject = projectListView.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            float total = selectedProject.getTotalTariffCost();
            totalTariffCostLabel.setText(String.format("Total Tariff Cost: %.2f", total));
        } else {
            totalTariffCostLabel.setText("Total Tariff Cost: N/A");
        }
    }

    private void handleLookupTariff() {
        try {

            int taricCode = Integer.parseInt(taricCodeField.getText().trim());
            String countryOrigin = countryOriginField.getText().trim();
            String shippingCountry = shippingCountryField.getText().trim();
            String deliveryCountry = deliveryCountryField.getText().trim();
            float value = Float.parseFloat(valueField.getText().trim());

            TariffInfo tariff = new TariffInfo(taricCode, countryOrigin, shippingCountry, deliveryCountry, value, 0f);
            TariffOpzoekService tariffService = new TariffOpzoekService();
            tariffService.calculateTariff(tariff);
            rateField.setText(String.valueOf(tariff.getRate()));

            tariffs.add(tariff);
            tariffTableView.refresh();
            System.out.println("Tariff updated: Rate = " + tariff.getRate()
                    + ", Calculated Duty = " + tariff.getCalculatedTariff());
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numeric values.");
        }
    }

    private void handleAddTariff() {
        try {
            int taricCode = Integer.parseInt(taricCodeField.getText());
            String countryOrigin = countryOriginField.getText();
            String shippingCountry = shippingCountryField.getText();
            String deliveryCountry = deliveryCountryField.getText();
            float value = Float.parseFloat(valueField.getText());
            float rate = Float.parseFloat(rateField.getText());
            if (!countryOrigin.isEmpty() && !shippingCountry.isEmpty() && !deliveryCountry.isEmpty()) {
                TariffInfo tariff = new TariffInfo(taricCode, countryOrigin, shippingCountry, deliveryCountry, value, rate);
                tariffs.add(tariff);
                tariffTableView.refresh();
                System.out.println("Added tariff: " + taricCode);
            } else {
                System.out.println("Please fill in all tariff details.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numeric values for code, value, and rate.");
        }
    }


    private void handleAddTariffToShipment() {
        TariffInfo selectedTariff = tariffComboBox.getSelectionModel().getSelectedItem();
        if (selectedTariff != null) {
            if (!tempShipmentTariffs.contains(selectedTariff)) {
                tempShipmentTariffs.add(selectedTariff);
                currentShipmentTariffListView.refresh();
                System.out.println("Added tariff " + selectedTariff.getTaricCode() + " to current shipment.");
            } else {
                System.out.println("Tariff already added to shipment.");
            }
        } else {
            System.out.println("Select a tariff to add.");
        }
    }


    private void handleCreateShipment() {
        String shipmentName = shipmentNameField.getText().trim();
        if (shipmentName.isEmpty()) {
            System.out.println("Shipment name cannot be empty.");
            return;
        }
        if (tempShipmentTariffs.isEmpty()) {
            System.out.println("Please add at least one tariff to the shipment.");
            return;
        }
        TariffOpzoekService tariffService = new TariffOpzoekService();


        Shipment shipment = new DefaultShipment(shipmentName, new ArrayList<>(tempShipmentTariffs), tariffService);
        shipments.add(shipment);

        Project selectedProject = projectListView.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            selectedProject.addShipment(shipment);
            System.out.println("Added shipment '" + shipmentName + "' to project: "
                    + selectedProject.getProjectName());
            updateTotalTariffCost();
        } else {
            System.out.println("No project selected. Shipment created but not assigned to any project.");
        }
        shipmentTableView.refresh();


        tempShipmentTariffs.clear();
        shipmentNameField.clear();
        currentShipmentTariffListView.refresh();
    }
}
