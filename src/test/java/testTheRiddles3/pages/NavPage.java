package testTheRiddles3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import testTheRiddles3.URL;

public class NavPage extends PageTemplate {
    @FindBy(xpath = "//a[contains(text(), 'Reptile Riddles')]")
    private WebElement homeButton;
    @FindBy(xpath = "//span[contains(text(), 'Games')]")
    private WebElement gamesButton;
    @FindBy(xpath = "//span[contains(text(), 'Quizzes')]")
    private WebElement quizzesButton;
    @FindBy(xpath = "//span[contains(text(), 'My Quizzes')]")
    private WebElement myQuizzesButton;
    @FindBy(xpath = "//span[contains(text(), 'Account')]")
    private WebElement accountButton;

    @FindBy(xpath = "//span[contains(text(), 'Login')]")
    private WebElement loginButton;
    @FindBy(xpath = "//span[contains(text(), 'Sign up')]")
    private WebElement signUpButton;
    @FindBy(xpath = "//span[contains(text(), 'Logout')]")
    private WebElement logoutButton;

    public NavPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(URL.BASE_URL, driver, wait, actions);
    }

    public void clickHomeButton() {
        homeButton.click();
    }

    public void clickGamesButton() {
        gamesButton.click();
    }

    public void clickQuizzesButton() {
        quizzesButton.click();
    }

    public void clickMyQuizzesButton() {
        myQuizzesButton.click();
    }

    public void clickAccountButton() {
        accountButton.click();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickSignUpButton() {
        signUpButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

}
