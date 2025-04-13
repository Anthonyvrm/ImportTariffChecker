package Classes;

import java.util.ArrayList;

public class Shipment {
    private String shipmentName;
    private ArrayList<TariffInfo> tariffInfos;
    private TariffOpzoekService tariffCost;


    public Shipment(String shipmentName, ArrayList<TariffInfo> tariffInfos, TariffOpzoekService tariffCost) {
        this.shipmentName = shipmentName;
        this.tariffInfos = tariffInfos;
        this.tariffCost = tariffCost;
    }

    public String getShipmentName() {
        return shipmentName;
    }

    public void setShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
    }

    public ArrayList<TariffInfo> getTariffInfos() {
        return tariffInfos;
    }

    public void setTariffInfos(ArrayList<TariffInfo> tariffInfos) {
        this.tariffInfos = tariffInfos;
    }

    public TariffOpzoekService getTariffCost() {
        return tariffCost;
    }

    public void setTariffCost(TariffOpzoekService tariffCost) {
        this.tariffCost = tariffCost;
    }

    public void validateShipment() {
        if (tariffInfos == null || tariffCost == null) {
            throw new IllegalArgumentException("TARIC information or cost is missing.");
        }

        for (TariffInfo info : tariffInfos) {
            if (info == null) {
                throw new IllegalArgumentException("One of the TARIC information entries is null.");
            }
        }
        System.out.println("Shipment '" + shipmentName + "' validated successfully.");
    }
}
