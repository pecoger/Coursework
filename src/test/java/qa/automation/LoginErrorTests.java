package qa.automation;

import base.TestUtils;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CsvHelper;

import java.io.IOException;

public class LoginErrorTests extends TestUtils {
    @DataProvider(name = "userList")
    public static Object[][] readValidCredentials() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/userList.csv");
    }

    @Test(dataProvider = "userList")
    public void checkLoginErrorMessage(String userName, String userPassword) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName, userPassword);
        Assert.assertTrue(loginPage.isLoginErrorMessageDisplayed(), "No error message displayed");
    }
}
