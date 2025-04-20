package Classes;


import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @org.junit.jupiter.api.Test
    void testGetProjectID() {
        Project project = new Project("Test Project");
        project.setProjectID(1);
        assertEquals(1, project.getProjectID(), "The project ID should be 1");
    }

    @org.junit.jupiter.api.Test
    void testGetProjectName() {
        Project project = new Project("Test Project");
        assertEquals("Test Project", project.getProjectName(), "The project name should be 'Test Project'");
    }

    @org.junit.jupiter.api.Test
    void testAddShipment() {
        Project project = new Project("Test Project");

        TariffOpzoekService tariffService = new TariffOpzoekService();
        ArrayList<TariffInfo> tariffInfos = new ArrayList<>();
        Shipment shipment = new Shipment("Test Shipment", tariffInfos, tariffService);
        project.addShipment(shipment);
        assertTrue(project.getShipments().contains(shipment), "The shipment should be added to the project");
    }

    @org.junit.jupiter.api.Test
    void testRemoveShipment() {
        Project project = new Project("Test Project");
        TariffOpzoekService tariffService = new TariffOpzoekService();
        ArrayList<TariffInfo> tariffInfos = new ArrayList<>();
        Shipment shipment = new Shipment("Test Shipment", tariffInfos, tariffService);
        project.addShipment(shipment);
        project.removeShipment(shipment);
        assertFalse(project.getShipments().contains(shipment), "The shipment should be removed from the project");
    }

    @org.junit.jupiter.api.Test
    void testGetTotalTariffCost() {
        Project project = new Project("Test Project");
        TariffOpzoekService tariffService = new TariffOpzoekService();
        ArrayList<TariffInfo> tariffInfos = new ArrayList<>();
        Shipment shipment = new Shipment("Test Shipment", tariffInfos, tariffService);

        TariffInfo tariffInfo = new TariffInfo(1000, "Germany", "France", "Italy", 100.0f, 0.05f);
        tariffInfo.setCalculatedTariff(100.0f);
        tariffInfos.add(tariffInfo);
        project.addShipment(shipment);

        assertEquals(100.0f, project.getTotalTariffCost(), "The total tariff cost should be 100.0f");
    }
}
