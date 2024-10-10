package testTheRiddles3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testTheRiddles3.URL;



public class LoginPage extends PageTemplate {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(text(), 'LOGIN')]")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='mb-8']/a/button[contains(text(), 'Sign up')]")
    private WebElement signupbutton;

    public LoginPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(URL.LOGIN_URL, driver, wait, actions);
    }

    public void logIn(String username, String password) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
        wait.until(ExpectedConditions.urlToBe(URL.BASE_URL+"/"));
    }
    public void clickLogin(){
        loginButton.click();
        wait.until(ExpectedConditions.urlToBe(URL.LOGIN_URL));
    }
    public void navigateToSignUpPageWithButton(){
        signupbutton.click();
        wait.until(ExpectedConditions.urlToBe(URL.SIGN_UP_URL));
    }

}
