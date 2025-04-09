package Controller;

import Classes.Project;
import Classes.TariffInfo;
import Classes.Shipment;
import Classes.TariffOpzoekService;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;

public class MainController {

    // ===== Login Tab =====
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    // ===== Project Tab =====
    // Note: Our Project class expects an int projectID and a String projectName.
    @FXML
    private TextField projectIDField; // New field added in the updated FXML.
    @FXML
    private TextField projectNameField;
    @FXML
    private Button createProjectButton;
    @FXML
    private Button updateProjectButton;
    @FXML
    private ListView<Project> projectListView;

    // ===== Tariffs Tab =====
    // TariffInfo expects: int tariffCode, String countryOfOrigin, String shippingCountry, String deliveryCountry, float value, float rate.
    @FXML
    private TextField tariffCodeField;
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
    private Button addTariffButton;
    @FXML
    private TableView<TariffInfo> tariffTableView;
    @FXML
    private TableColumn<TariffInfo, String> tariffCodeColumn;
    @FXML
    private TableColumn<TariffInfo, String> countryOriginColumn;
    @FXML
    private TableColumn<TariffInfo, Number> rateColumn;

    // ===== Shipment Tab =====
    // Shipment takes a TariffInfo and a TariffOpzoekService.
    // We removed shipmentNameField because Shipment doesn't include a name.
    @FXML
    private ComboBox<TariffInfo> tariffComboBox;
    @FXML
    private Button addShipmentButton; // Renamed from addTariffToShipmentButton.
    @FXML
    private TableView<Shipment> shipmentTableView;
    // We'll display the tariff code from the tariffInfo in the shipment.
    @FXML
    private TableColumn<Shipment, String> tariffCodeShipmentColumn;

    // Data lists for UI components
    private ObservableList<Project> projects = FXCollections.observableArrayList();
    private ObservableList<TariffInfo> tariffs = FXCollections.observableArrayList();
    private ObservableList<Shipment> shipments = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize Tariff TableView columns.
        tariffCodeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getTariffCode()))
        );
        countryOriginColumn.setCellValueFactory(new PropertyValueFactory<>("countryOfOrigin"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));
        tariffTableView.setItems(tariffs);

        // Initialize Project ListView.
        projectListView.setItems(projects);

        // Initialize Shipment TableView.
        // We display the tariff code from the Shipment's TariffInfo.
        tariffCodeShipmentColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getTariffInfo().getTariffCode()))
        );
        shipmentTableView.setItems(shipments);

        // Initialize Tariff ComboBox.
        tariffComboBox.setItems(tariffs);

        // Set up button action listeners.
        loginButton.setOnAction(event -> handleLogin());
        createProjectButton.setOnAction(event -> handleCreateProject());
        updateProjectButton.setOnAction(event -> handleUpdateProject());
        lookupTariffButton.setOnAction(event -> handleLookupTariff());
        addTariffButton.setOnAction(event -> handleAddTariff());
        addShipmentButton.setOnAction(event -> handleAddShipment());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        // Dummy authentication logic.
        if ("user".equals(username) && "pass".equals(password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
    }

    private void handleCreateProject() {
        try {
            int projectID = Integer.parseInt(projectIDField.getText());
            String projectName = projectNameField.getText();
            if (!projectName.isEmpty()) {
                Project project = new Project(projectID, projectName);
                projects.add(project);
                projectListView.setItems(projects);
                System.out.println("Created project: " + projectName);
            } else {
                System.out.println("Project name cannot be empty.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid project ID.");
        }
    }

    private void handleUpdateProject() {
        Project selectedProject = projectListView.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            try {
                int newProjectID = Integer.parseInt(projectIDField.getText());
                String newProjectName = projectNameField.getText();
                selectedProject.setProjectID(newProjectID);
                selectedProject.setProjectName(newProjectName);
                projectListView.refresh();
                System.out.println("Updated project: " + selectedProject.getProjectName());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid project ID.");
            }
        } else {
            System.out.println("Select a project to update.");
        }
    }

    private void handleLookupTariff() {
        try {
            int code = Integer.parseInt(tariffCodeField.getText());
            for (TariffInfo tariff : tariffs) {
                if (tariff.getTariffCode() == code) {
                    tariffTableView.getSelectionModel().select(tariff);
                    System.out.println("Found tariff: " + code);
                    return;
                }
            }
            System.out.println("No tariff found with code: " + code);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid tariff code.");
        }
    }

    private void handleAddTariff() {
        try {
            int code = Integer.parseInt(tariffCodeField.getText());
            String countryOrigin = countryOriginField.getText();
            String shippingCountry = shippingCountryField.getText();
            String deliveryCountry = deliveryCountryField.getText();
            float value = Float.parseFloat(valueField.getText());
            float rate = Float.parseFloat(rateField.getText());
            if (!countryOrigin.isEmpty() && !shippingCountry.isEmpty() && !deliveryCountry.isEmpty()) {
                TariffInfo tariff = new TariffInfo(code, countryOrigin, shippingCountry, deliveryCountry, value, rate);
                tariffs.add(tariff);
                tariffTableView.refresh();

                System.out.println("Added tariff: " + code);
            } else {
                System.out.println("Please fill in all tariff details.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numeric values for code, value, and rate.");
        }
    }

    private void handleAddShipment() {
        TariffInfo selectedTariff = tariffComboBox.getSelectionModel().getSelectedItem();
        if (selectedTariff != null) {
            // Create a new TariffOpzoekService instance.
            TariffOpzoekService tariffService = new TariffOpzoekService();
            Shipment shipment = new Shipment(selectedTariff, tariffService);
            shipments.add(shipment);
            shipmentTableView.refresh();
            System.out.println("Added shipment for tariff: " + selectedTariff.getTariffCode());
        } else {
            System.out.println("Select a tariff to create a shipment.");
        }
    }
}
