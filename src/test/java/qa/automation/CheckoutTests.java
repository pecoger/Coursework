package qa.automation;

import base.TestUtils;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.CsvHelper;

import java.io.IOException;

public class CheckoutTests extends TestUtils {
    @DataProvider(name = "validCredentials")
    public static Object[][] readValidCredentials() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/validCredentials.csv");
    }

    @Test(dataProvider = "validCredentials")
    public void finishTheOrder(String userName, String userPassword) {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, userPassword);

        productsPage.addItemToCart("fleece-jacket");
        productsPage.enterIntoCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.checkout();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
        checkoutInformationPage.fillCheckoutInfo("Petko", "Gerdemski", "8600");

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.finishCheckout();

        CompletedOrderPage completedOrderPage = new CompletedOrderPage(driver);
        Assert.assertTrue(completedOrderPage.getCheckoutHeader());
    }
}
