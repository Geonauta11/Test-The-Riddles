package testTheRiddles3.macro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testTheRiddles3.URL;
import testTheRiddles3.pages.LoginPage;
import testTheRiddles3.pages.SignUpPage;
import testTheRiddles3.pages.quizzes.QuestionData;

public class UserMacro{
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private UserInfo userInfo;

    public UserMacro(WebDriver driver, WebDriverWait wait, Actions actions) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
    }

    public void createCredentialsFromTimestamp(long timestamp){
        userInfo = new UserInfo(
            "username%d".formatted(timestamp),
            "email%d@email.com".formatted(timestamp),
            "password%d".formatted(timestamp)
        );
    }

    public void register() {
        driver.get(URL.SIGN_UP_URL);
        SignUpPage signUpPage = new SignUpPage(driver, wait, actions);
        signUpPage.register(userInfo.username(), userInfo.email(), userInfo.password());
    }

    public void login() {
        driver.get(URL.LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver, wait, actions);
        loginPage.logIn(userInfo.username(), userInfo.password());
    }

}
