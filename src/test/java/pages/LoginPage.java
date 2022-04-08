package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    protected WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//*[text()='Epic sadface: Username and password do not match any user in this service']")
    private WebElement loginErrorMismatch;

    @FindBy(xpath = "//*[text()='Epic sadface: Password is required']")
    private WebElement loginErrorPassword;

    @FindBy(xpath = "//*[text()='Epic sadface: Username is required']")
    private WebElement loginErrorUsername;

    @FindBy(xpath = "//button[@class='error-button']")
    private WebElement buttonForCLosingErroMessages;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // this method logs into the page
    public ProductsPage login(String username, String password){
        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys(username);

        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginButton.click();

        return new ProductsPage(driver);
    }
//    this method check if a message is displayed when there is login error
    public boolean isLoginErrorMessageDisplayed(){
            return buttonForCLosingErroMessages.isDisplayed();
    }
}
