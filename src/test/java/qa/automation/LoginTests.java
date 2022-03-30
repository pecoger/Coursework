package qa.automation;

import base.TestUtils;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;

import java.io.IOException;

public class LoginTests extends TestUtils {

    @DataProvider(name = "validCredentials")
    public static Object[][] readValidCredentials() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/validCredentials.csv");
    }

    @Test(dataProvider = "validCredentials")
    public void SuccessfulLogin(String userName, String userPassword){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, userPassword);

        WebElement burgerMenu = driver.findElement(By.id("react-burger-menu-btn"));
        Assert.assertTrue(burgerMenu.isDisplayed());
    }

    @DataProvider(name = "invalidCredentials")
    public static Object[][] readInvalidCredentials() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/invalidCredentials.csv");
    }

    @Test(dataProvider = "invalidCredentials")
    public void UnsuccessfulLogin(String userName, String userPassword) {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, userPassword);

        WebElement loginError = driver.findElement(By.xpath("//*[text()='Epic sadface: Username and password do not match any user in this service']"));
        Assert.assertTrue(loginError.isDisplayed());
    }
}
