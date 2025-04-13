package Classes;

import java.util.ArrayList;

public class DefaultShipment extends Shipment {

    public DefaultShipment(String shipmentName, ArrayList<TariffInfo> tariffInfos, TariffOpzoekService tariffCost) {
        super(shipmentName, tariffInfos, tariffCost);
    }

    @Override
    public void validateShipment() {

        super.validateShipment();

        System.out.println("DefaultShipment: Shipment validated successfully.");
    }
}
