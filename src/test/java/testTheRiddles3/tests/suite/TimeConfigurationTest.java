package testTheRiddles3.tests.suite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testTheRiddles3.URL;
import testTheRiddles3.macro.UserMacro;
import testTheRiddles3.pages.quizzes.AnswerData;
import testTheRiddles3.pages.quizzes.MyQuizzesPage;
import testTheRiddles3.pages.quizzes.QuestionData;
import testTheRiddles3.tests.TestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TimeConfigurationTest extends TestTemplate {
    UserMacro userMacro;

    @BeforeEach
    public void setUp() {
        userMacro = new UserMacro(driver, wait, actions);
        userMacro.createCredentialsFromTimestamp(timestamp);
        userMacro.register();
        userMacro.login();
        driver.get(URL.MY_QUIZZES_URL);
    }

    @Test
    public void negativeTimeConfigurationTest() {
        MyQuizzesPage myQuizzesPage = new MyQuizzesPage(driver, wait, actions);
        String quizName = "quiz%d".formatted(timestamp);
        myQuizzesPage.createSingleQuiz(
                quizName,
                        new QuestionData(
                                "question1",
                                "-42",
                                List.of(
                                        new AnswerData("answer1", true),
                                        new AnswerData("answer2", false)
                                ))
        );
        assertTrue(negativeTimeErrorIsPresent());

    }

    private boolean negativeTimeErrorIsPresent() {
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        return alertText.equals("Error, no negative time can be accepted!");
    }
}
