package qa.automation;

import base.TestUtils;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;

import java.io.IOException;

public class LoginTests extends TestUtils {

    @DataProvider(name = "userList")
    public static Object[][] readValidCredentials() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/userList.csv");
    }

    @Test(dataProvider = "userList")
    public void tryLogin(String userName, String userPassword){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, userPassword);
        productsPage.isBurgerMenuDisplayed();
        Assert.assertTrue(productsPage.isBurgerMenuDisplayed());
    }
}