package testTheRiddles3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testTheRiddles3.URL;

public class SignUpPage extends PageTemplate {
    @FindBy(id = "user-name")
    private WebElement usernameinput;

    @FindBy(id = "email")
    private WebElement emailinput;

    @FindBy(id = "password")
    private WebElement passwordinput;

    @FindBy(xpath = "//button[contains(text(), 'SIGN UP')]")
    private WebElement signupbutton;

    @FindBy(xpath = "//div[@class='mb-8']/a[@href='/login']/button")
    private WebElement tologinpagebutton;

    public SignUpPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(URL.SIGN_UP_URL, driver, wait, actions);
    }

    public void register(String username, String email, String password) {
        setInput(usernameinput, username);
        setInput(emailinput, email);
        setInput(passwordinput, password);
        click(signupbutton);
        wait.until(ExpectedConditions.urlToBe(URL.LOGIN_URL));
    }

    public void navigateToLoginWithButton() {
        click(tologinpagebutton);
        wait.until(ExpectedConditions.urlToBe(URL.LOGIN_URL));
    }

    public void registerWithExistingCredentials(String username, String password, String email) {
        register(username, email, password);
        driver.navigate().to(URL.SIGN_UP_URL);
        register(username, email, password);
    }

    private boolean hasAlert(WebElement inputField) {
        return inputField.getAttribute("validationMessage").contains("Please fill out this field.");
    }

    public boolean usernameHasAlert() {
        return hasAlert(usernameinput);
    }

    public boolean emailHasAlert() {
        return hasAlert(emailinput);
    }

    public boolean passwordHasAlert() {
        return hasAlert(passwordinput);
    }

}
