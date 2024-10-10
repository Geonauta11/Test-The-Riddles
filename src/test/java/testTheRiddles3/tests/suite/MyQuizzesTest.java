package testTheRiddles3.tests.suite;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testTheRiddles3.URL;
import testTheRiddles3.pages.LoginPage;
import testTheRiddles3.pages.NavPage;
import testTheRiddles3.pages.SignUpPage;
import testTheRiddles3.pages.quizzes.AddQuestionPage;
import testTheRiddles3.pages.quizzes.AddQuizPage;
import testTheRiddles3.pages.quizzes.MyQuizzesPage;
import testTheRiddles3.tests.TestTemplate;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyQuizzesTest extends TestTemplate {

    private long id;
    private MyQuizzesPage myQuizzesPage;
    private AddQuizPage addQuizPage;
    private AddQuestionPage addQuestionPage;

    @BeforeAll
    void init() {
        id = timestamp;
        driver.get(URL.SIGN_UP_URL);
        new SignUpPage(driver, wait, actions).register("TestUser-" + id, "test" + id + "@.email.com", "testPassword");
        driver.get(URL.LOGIN_URL);
        new LoginPage(driver, wait, actions).logIn("TestUser-" + id, "testPassword");
        new NavPage(driver, wait, actions).clickMyQuizzesButton();
        myQuizzesPage = new MyQuizzesPage(driver, wait, actions);
    }

    @BeforeEach
    void initEach() {
        id = timestamp;
        driver.get(URL.MY_QUIZZES_URL);
        myQuizzesPage.addQuiz();
        addQuizPage = new AddQuizPage(driver, wait, actions);
    }


    @Test
    void deleteQuiz() {
        addQuizPage.createQuiz("Delete me pls-" + id);
        driver.get(URL.MY_QUIZZES_URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text() = '%s']/..".formatted("Delete me pls-" + id))));
        myQuizzesPage.deleteQuiz("Delete me pls-" + id);
        assertThrows(NoSuchElementException.class, () -> myQuizzesPage.getQuizByName("Delete me pls-" + id));
    }

    @Test
    void updateTimerWithValidInputs() {
        addQuestionPage = new AddQuestionPage(driver, wait, actions);
        addQuizPage.createQuiz("Update me pls-" + id);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text() = '%s']/..".formatted("Update me pls-" + id))));
        myQuizzesPage.editQuiz("Update me pls-" + id);
        wait.until(ExpectedConditions.elementToBeClickable(addQuizPage.getQuestion("TestQuestion-1")));
        addQuestionPage.updateTimer(addQuizPage.getQuestion("TestQuestion-1"));
        wait.until(ExpectedConditions.elementToBeClickable(addQuizPage.getQuestion("TestQuestion-1")));
        addQuizPage.getQuestion("TestQuestion-1").click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Time limit (seconds) ')]/following-sibling::input")));
        assertEquals("180", addQuestionPage.getTimeLimit());
    }


    @Test
    void addAnswerOption() {
        addQuestionPage = new AddQuestionPage(driver, wait, actions);
        addQuizPage.createQuiz("Update me pls-" + id);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text() = '%s']/..".formatted("Update me pls-" + id))));
        myQuizzesPage.editQuiz("Update me pls-" + id);
        wait.until(ExpectedConditions.elementToBeClickable(addQuizPage.getQuestion("TestQuestion-1")));
        addQuestionPage.addNewOption("newOption", false, addQuizPage.getQuestion("TestQuestion-1"));
        wait.until(ExpectedConditions.elementToBeClickable(addQuizPage.getQuestion("TestQuestion-1")));
        addQuizPage.getQuestion("TestQuestion-1").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), 'x')]")));
        assertEquals(3, addQuestionPage.getNumberAnswers());
    }

}

