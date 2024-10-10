package testTheRiddles3.tests.suite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testTheRiddles3.URL;
import testTheRiddles3.pages.LoginPage;
import testTheRiddles3.pages.SignUpPage;
import testTheRiddles3.tests.TestTemplate;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends TestTemplate {
    LoginPage loginPage;
    SignUpPage signUpPage;
    String username, password, email;



    @BeforeEach
    void setUpLoginTest() {
        driver.get(URL.SIGN_UP_URL);
        signUpPage = new SignUpPage(driver, wait, actions);
        username = "username%d".formatted(timestamp);
        password = "password";
        email = "email%d".formatted(timestamp);
        signUpPage.register(username, email, password);
        driver.get(URL.LOGIN_URL);
        loginPage = new LoginPage(driver, wait, actions);
    }

    @Test
    public void loginPage_logIn_redirectsToBaseUrl() {
        loginPage.logIn(username, password);
        assertEquals(URL.BASE_URL + "/", driver.getCurrentUrl());
    }

    @Test
    void loggedInRememberMe_refreshPage_staysLoggedIn() {
        throw new RuntimeException("checkbox not in page");
    }

    @Test
    void loggedInDontRememberMe_refreshPage_logsOut() {
        throw new RuntimeException("checkbox not in page");
    }

    @Test
    void loggedInRememberMe_closeAndOpenBrowser_staysLoggedIn() {
        throw new RuntimeException("checkbox not in page");
    }

    @Test
    void loggedInDontRememberMe_closeAndOpenBrowser_logsOut() {
        throw new RuntimeException("checkbox not in page");
    }
}
