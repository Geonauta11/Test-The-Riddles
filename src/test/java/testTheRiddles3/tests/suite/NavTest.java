package testTheRiddles3.tests.suite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testTheRiddles3.URL;
import testTheRiddles3.pages.NavPage;
import testTheRiddles3.tests.TestTemplate;
import static org.junit.jupiter.api.Assertions.*;


public class NavTest extends TestTemplate {
    NavPage navPage;
    @BeforeEach
    void setUpLoginTest() {
        driver.get(URL.BASE_URL);
        navPage = new NavPage(driver, wait, actions);
    }

    @Test
    void mainPage_clickLoginButton_goesToLoginPage() {
        navPage.clickLoginButton();
        assertEquals(URL.LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    void mainPage_clickSignInButton_goesToSignInPage() {
        navPage.clickSignUpButton();
        assertEquals(URL.SIGN_UP_URL, driver.getCurrentUrl());
    }

    @Test
    void mainPageLoggedOut_clickMyQuizzes_goesToLoginPage() {
        navPage.clickMyQuizzesButton();
        assertEquals(URL.LOGIN_URL, driver.getCurrentUrl());

    }
}
