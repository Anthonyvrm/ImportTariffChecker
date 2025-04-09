package Classes;

public class TariffOpzoekService {


    public float lookupRate(int tariffCode, String countryOfOrigin, String shippingCountry, String deliveryCountry) {
// bij nader inzien is tarief opzoeken super lastig dus heb ik het maar zo gedaan met soortvan ruwe gokken anders werd het te lastig

        if (tariffCode >= 1000 && tariffCode < 2000) {
            return 0.05f; // 5%
        } else if (tariffCode >= 2000 && tariffCode < 3000) {
            return 0.10f; // 10%
        } else if (tariffCode >= 3000 && tariffCode < 4000) {
            return 0.15f; // 15%
        } else {
            return 0.08f; // standaard 8%
        }
    }


    public void calculateTariff(TariffInfo tariffInfo) {

        int tariffCode = tariffInfo.getTariffCode();
        String countryOfOrigin = tariffInfo.getCountryOfOrigin();
        String shippingCountry = tariffInfo.getShippingCountry();
        String deliveryCountry = tariffInfo.getDeliveryCountry();
        float value = tariffInfo.getValue();


        float rate = lookupRate(tariffCode, countryOfOrigin, shippingCountry, deliveryCountry);


        tariffInfo.setRate(rate);


        float calculatedTariff = value * rate;


        tariffInfo.setCalculatedTariff(calculatedTariff);

        System.out.println("Tariff calculated: Value = " + value + ", Rate = " + rate + ", Duty = " + calculatedTariff);
    }

    public void checkTradeAgreements() {

        System.out.println("Checking trade agreements...");
    }
}
