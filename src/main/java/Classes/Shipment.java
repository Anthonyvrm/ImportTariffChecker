package Classes;

public class Shipment {
    private TariffInfo tariffInfo;
    private TariffOpzoekService tariffCost;
    public Shipment(TariffInfo tariffInfo, TariffOpzoekService tariffCost) {
        this.tariffInfo = tariffInfo;
        this.tariffCost = tariffCost;
    }
    public TariffInfo getTariffInfo() {
        return tariffInfo;
    }
    public void setTariffInfo(TariffInfo tariffInfo) {
        this.tariffInfo = tariffInfo;
    }
    public TariffOpzoekService getTariffCost() {
        return tariffCost;
    }
    public void setTariffCost(TariffOpzoekService tariffCost) {
        this.tariffCost = tariffCost;
    }
    public void validateShipment() {
        // Validate shipment details
        if (tariffInfo == null || tariffCost == null) {
            throw new IllegalArgumentException("Tariff information or cost is missing.");
        }
        // Additional validation logic can be added here
    }
}
