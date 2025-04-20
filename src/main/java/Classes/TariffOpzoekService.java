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


    public void calculateTariff(TariffInfo info, TradeAgreements agreement) {

        float rate = lookupRate(
                info.getTaricCode(),
                info.getCountryOfOrigin(),
                info.getShippingCountry(),
                info.getDeliveryCountry()
        );
        info.setRate(rate);
        float baseTariff = info.getValue() * rate;


        float discount = 0;
        if (agreement != null
                && agreement.getUsableCountries().contains(info.getCountryOfOrigin())
                && agreement.getUsableCountries().contains(info.getDeliveryCountry())
        ) {
            discount = baseTariff * agreement.getDiscountRate();
            System.out.println("Applied " + agreement.getName() +
                    " discount: " + (agreement.getDiscountRate() * 100) + "%");
        }

        float finalTariff = baseTariff - discount;
        info.setCalculatedTariff(finalTariff);

        System.out.println(String.format(
                "Tariff calculated: base=%.2f, discount=%.2f, final=%.2f",
                baseTariff, discount, finalTariff
        ));
        info.setAppliedAgreement(agreement);


    }


    public void checkTradeAgreements() {

        System.out.println("Checking trade agreements...");
    }
}
