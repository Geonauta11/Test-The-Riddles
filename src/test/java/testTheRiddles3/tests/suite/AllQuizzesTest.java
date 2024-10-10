package testTheRiddles3.tests.suite;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testTheRiddles3.URL;
import testTheRiddles3.pages.LoginPage;
import testTheRiddles3.pages.NavPage;
import testTheRiddles3.pages.SignUpPage;
import testTheRiddles3.pages.quizzes.AddQuizPage;
import testTheRiddles3.pages.quizzes.AllQuizzesPage;
import testTheRiddles3.pages.quizzes.MyQuizzesPage;
import testTheRiddles3.tests.TestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllQuizzesTest extends TestTemplate {

    private AllQuizzesPage allQuizzesPage;
    private long id;

    @BeforeAll
    void init() {
        id = timestamp;
        driver.get(URL.SIGN_UP_URL);
        new SignUpPage(driver, wait, actions).register("TestUser-" + id, "test" + id + "@.email.com", "testPassword");
        driver.get(URL.LOGIN_URL);
        new LoginPage(driver, wait, actions).logIn("TestUser-" + id, "testPassword");
        new NavPage(driver, wait, actions).clickMyQuizzesButton();
        new MyQuizzesPage(driver, wait, actions).addQuiz();
        new AddQuizPage(driver, wait, actions).createQuiz("TestQuiz-" + id);
        driver.get(URL.BASE_URL);
        new NavPage(driver, wait, actions).clickLogoutButton();
        driver.get(URL.SIGN_UP_URL);
        long newId = System.currentTimeMillis();
        new SignUpPage(driver, wait, actions).register("TestUser" + newId, "test" + newId + "@.email.com", "testPassword");
        driver.get(URL.LOGIN_URL);
        new LoginPage(driver, wait, actions).logIn("TestUser" + newId, "testPassword");
        driver.get(URL.ALL_QUIZ_URL);
        allQuizzesPage = new AllQuizzesPage(driver, wait, actions);


    }


    @Test
    void getQuizTest() {
        assertTrue(Optional.ofNullable(allQuizzesPage.getQuizByName("TestQuiz-" + id)).isPresent());
    }

    @Test
    void copyQuizTest() {
        allQuizzesPage.copyQuiz("TestQuiz-" + id);
        assertTrue(driver.getCurrentUrl().contains("quizform"));
    }
}
