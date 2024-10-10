package testTheRiddles3.tests.suite;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;
import testTheRiddles3.URL;
import testTheRiddles3.pages.SignUpPage;
import testTheRiddles3.tests.TestTemplate;
import static org.junit.jupiter.api.Assertions.*;

public class SignUpTest extends TestTemplate {
    SignUpPage signUpPage;

    @BeforeEach
    public void setUp() {
        driver.get(URL.SIGN_UP_URL);
        signUpPage = new SignUpPage(driver, wait, actions);
    }

    @Test
    public void signUpPage_signUpWithValidCredentials_redirectsToLoginPage() {
        signUpPage.register("username%d".formatted(timestamp),
                "email%d@gmail.com".formatted(timestamp),
                "password");
        assertTrue(signUpPage.isUrlRight(URL.LOGIN_URL));
    }

    @Test
    public void signUpPage_signUpWithRepeatCredentials_staysOnSignUpPage() {
        try {
            signUpPage.register("username%d".formatted(timestamp),
                "email%d@gmail.com".formatted(timestamp),
                "password");
            driver.get(URL.SIGN_UP_URL);
        } catch (Exception e) {
            throw new TestAbortedException("Sign up failed", e);
        }
        signUpPage.register("username%d".formatted(timestamp),
            "email%d@gmail.com".formatted(timestamp),
            "password");
        assertEquals(URL.SIGN_UP_URL, driver.getCurrentUrl());
    }

    @Test
    public void signUpPage_clickLogInButton_navigatesToLogIn() {
        signUpPage.navigateToLoginWithButton();
        assertTrue(signUpPage.isUrlRight(URL.LOGIN_URL));
    }

    @Test
    public void signUpPage_signUpWithEmptyUsername_throwsError() {
        signUpPage.register("",
            "email%d@gmail.com".formatted(timestamp),
            "password");
        assertEquals(URL.SIGN_UP_URL, driver.getCurrentUrl());
        assertTrue(signUpPage.usernameHasAlert());
    }

    @Test
    public void signUpPage_signUpWithEmptyEmail_throwsError() {
        signUpPage.register("username%d".formatted(timestamp),
            "",
            "password");
        assertEquals(URL.SIGN_UP_URL, driver.getCurrentUrl());
        assertTrue(signUpPage.emailHasAlert());

    }

    @Test
    public void signUpPage_signUpWithEmptyPassword_throwsError() {
        signUpPage.register("username%d".formatted(timestamp),
            "email%d@gmail.com".formatted(timestamp),
            "");
        assertEquals(URL.SIGN_UP_URL, driver.getCurrentUrl());
        assertTrue(signUpPage.passwordHasAlert());

    }
}
