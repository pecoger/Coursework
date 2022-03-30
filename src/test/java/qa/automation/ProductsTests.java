package qa.automation;

import base.TestUtils;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;

import java.io.IOException;

public class ProductsTests extends TestUtils {

    @DataProvider(name = "validCredentials")
    public static Object[][] readValidCredentials() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/validCredentials.csv");
    }

    @Test(dataProvider = "validCredentials")
    public void addToCart(String userName, String userPassword) throws IOException, CsvException {
            LoginPage loginPage = new LoginPage(driver);
            ProductsPage productsPage = loginPage.login(userName, userPassword);
            ProductsPage testAddToCart = new ProductsPage(driver);
            testAddToCart.addItemToCart("Bolt T-Shirt");
    }
}
