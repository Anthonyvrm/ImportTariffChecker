package Classes;

public class TariffInfo {
    private int taricCode;
    private String countryOfOrigin;
    private String shippingCountry;
    private String deliveryCountry;
    private float value;
    private float rate;
    private float calculatedTariff;

    public TariffInfo(int taricCode, String countryOfOrigin, String shippingCountry, String deliveryCountry, float value, float rate) {
        this.taricCode = taricCode;
        this.countryOfOrigin = countryOfOrigin;
        this.shippingCountry = shippingCountry;
        this.deliveryCountry = deliveryCountry;
        this.value = value;
        this.rate = rate;
    }

    public int getTaricCode() {
        return taricCode;
    }

    public void setTaricCode(int taricCode) {
        this.taricCode = taricCode;
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
    public String toString() {

        return "Tariff " + taricCode + " (" + countryOfOrigin + " -> " + deliveryCountry + ")";
    }
}
