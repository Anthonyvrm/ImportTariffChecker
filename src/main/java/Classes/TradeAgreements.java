package Classes;

import java.util.ArrayList;

public class TradeAgreements {
    private String name;
    private float discountRate;
    private ArrayList<String> usableCountries;
    public TradeAgreements(String name, float discountRate) {
        this.name = name;
        this.discountRate = discountRate;
        this.usableCountries = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getDiscountRate() {
        return discountRate;
    }
    public void setDiscountRate(float discountRate) {
        this.discountRate = discountRate;
    }
    public ArrayList<String> getUsableCountries() {
        return usableCountries;
    }
    public void setUsableCountries(ArrayList<String> usableCountries) {
        this.usableCountries = usableCountries;
    }
    public void addUsableCountry(String country) {
        this.usableCountries.add(country);
    }
    public void removeUsableCountry(String country) {
        this.usableCountries.remove(country);
    }

}
