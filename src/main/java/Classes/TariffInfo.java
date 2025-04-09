package Classes;

public class TariffInfo {
    private int tariffCode;
    private String countryOfOrigin;
    private String shippingCountry;
    private String deliveryCountry;
    private float value;
    private float rate;
    private float calculatedTariff;  // Toegevoegd voor het berekende tarief

    public TariffInfo(int tariffCode, String countryOfOrigin, String shippingCountry, String deliveryCountry, float value, float rate) {
        this.tariffCode = tariffCode;
        this.countryOfOrigin = countryOfOrigin;
        this.shippingCountry = shippingCountry;
        this.deliveryCountry = deliveryCountry;
        this.value = value;
        this.rate = rate;
    }



    public int getTariffCode() {
        return tariffCode;
    }

    public void setTariffCode(int tariffCode) {
        this.tariffCode = tariffCode;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getDeliveryCountry() {
        return deliveryCountry;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getCalculatedTariff() {
        return calculatedTariff;
    }

    public void setCalculatedTariff(float calculatedTariff) {
        this.calculatedTariff = calculatedTariff;
    }
}
