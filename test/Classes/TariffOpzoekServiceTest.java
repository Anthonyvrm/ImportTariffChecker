package Classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TariffOpzoekServiceTest {

    private TariffOpzoekService tariffService;

    @BeforeEach
    void setUp() {
        tariffService = new TariffOpzoekService();
    }


    @Test
    void testLookupRate() {

        assertEquals(0.05f, tariffService.lookupRate(1500, "Germany", "France", "Italy"));
        assertEquals(0.10f, tariffService.lookupRate(2500, "Germany", "France", "Italy"));
        assertEquals(0.15f, tariffService.lookupRate(3500, "Germany", "France", "Italy"));
        assertEquals(0.08f, tariffService.lookupRate(4500, "Germany", "France", "Italy"));
    }


    @Test
    void testCalculateTariff_NoAgreement() {

        TariffInfo info = new TariffInfo(1500, "Germany", "France", "Italy", 1000f, 0.05f);
        info.setCalculatedTariff(0);
        tariffService.calculateTariff(info, null);


        assertEquals(50.0f, info.getCalculatedTariff(), "The calculated tariff should be 50.0 (1000 * 0.05)");
    }


    @Test
    void testCalculateTariff_WithAgreement() {

        TariffInfo info = new TariffInfo(1500, "Germany", "France", "Italy", 1000f, 0.05f);
        info.setCalculatedTariff(0);

        TradeAgreements agreement = new TradeAgreements("Agreement1", 0.1f);
        agreement.addUsableCountry("Germany");
        agreement.addUsableCountry("Italy");


        tariffService.calculateTariff(info, agreement);


        assertEquals(45.0f, info.getCalculatedTariff(), "The final tariff should be 45.0 (50.0 - 5.0 discount)");
    }


}
