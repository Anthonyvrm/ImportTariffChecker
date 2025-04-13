package Classes;

public class TariffOpzoekService {


    public float lookupRate(int taricCode, String countryOfOrigin, String shippingCountry, String deliveryCountry) {
// bij nader inzien is tarief opzoeken super lastig dus heb ik het maar zo gedaan met soortvan ruwe gokken anders werd het te lastig

        if (taricCode >= 1000 && taricCode < 2000) {
            return 0.05f; // 5%
        } else if (taricCode >= 2000 && taricCode < 3000) {
            return 0.10f; // 10%
        } else if (taricCode >= 3000 && taricCode < 4000) {
            return 0.15f; // 15%
        } else {
            return 0.08f; // standaard 8%
        }
    }


    public void calculateTariff(TariffInfo tariffInfo) {

        int taricCode = tariffInfo.getTaricCode();
        String countryOfOrigin = tariffInfo.getCountryOfOrigin();
        String shippingCountry = tariffInfo.getShippingCountry();
        String deliveryCountry = tariffInfo.getDeliveryCountry();
        float value = tariffInfo.getValue();


        float rate = lookupRate(taricCode, countryOfOrigin, shippingCountry, deliveryCountry);


        tariffInfo.setRate(rate);


        float calculatedTariff = value * rate;


        tariffInfo.setCalculatedTariff(calculatedTariff);

        System.out.println("Tariff calculated: Value = " + value + ", Rate = " + rate + ", Duty = " + calculatedTariff);
    }

    public void checkTradeAgreements() {

        System.out.println("Checking trade agreements...");
    }
}
