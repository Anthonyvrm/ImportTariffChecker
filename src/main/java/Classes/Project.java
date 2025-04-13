package Classes;

import java.util.ArrayList;

public class Project {
    private int projectID;
    private String projectName;
    private ArrayList<Shipment> shipments;

    public Project(String projectName) {
        this.projectName = projectName;
        this.shipments = new ArrayList<>();
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ArrayList<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(ArrayList<Shipment> shipments) {
        this.shipments = shipments;
    }

    public void addShipment(Shipment shipment) {
        this.shipments.add(shipment);
    }

    public void removeShipment(Shipment shipment) {
        this.shipments.remove(shipment);
    }


    public float getTotalTariffCost() {
        float total = 0;
        for (Shipment s : shipments) {
            for (TariffInfo info : s.getTariffInfos()) {
                total += info.getCalculatedTariff();
            }
        }
        return total;
    }

    public void shareProject() {
        System.out.println("Project " + projectName + " shared successfully.");
    }

    public void importProject() {
        System.out.println("Project " + projectName + " imported successfully.");
    }
}
