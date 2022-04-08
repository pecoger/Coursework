package qa.automation;

import base.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProblemUserImageTest extends TestUtils {

    @Test
    public void verifyImage() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("problem_user", "secret_sauce");
        Assert.assertTrue(productsPage.checkImage("bolt-shirt-1200x1500.c0dae290.jpg"));
    }
}
