package testTheRiddles3.pages.quizzes;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testTheRiddles3.URL;
import testTheRiddles3.pages.PageTemplate;

import java.util.List;
import java.util.Optional;

public class MyQuizzesPage extends PageTemplate {
    private final String quizButtonXpath = "//span[contains(text(), '%s')]/following-sibling::button[contains(text(), '%s')]";

    @FindBy(xpath = "//button[contains(text(), 'Add Quiz')]")
    private WebElement addQuizButton;

    public MyQuizzesPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(URL.MY_QUIZZES_URL, driver, wait, actions);
    }

    private WebElement getQuizButton(String quizName, QuizButtonType type) {
        return driver.findElement(By.xpath(quizButtonXpath.formatted(quizName, type.buttonText)));
    }

    public void editQuiz(String quizName) {
        click(getQuizButton(quizName, QuizButtonType.EDIT));
    }

    public void deleteQuiz(String quizName) {
        click(getQuizButton(quizName, QuizButtonType.DELETE));
        acceptAlert();
    }

    public void playQuiz(String quizName) {
        click(getQuizButton(quizName, QuizButtonType.PLAY));
    }

    public void addQuiz() {
        click(addQuizButton);
    }


    public WebElement getQuizByName(String quizName) throws NoSuchElementException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text() = '%s']/..".formatted(quizName))));
        return Optional.ofNullable(driver.findElement(By.xpath("//span[text() = '%s']/..".formatted(quizName)))).orElseThrow(()-> new NoSuchElementException("no element found"));

    }



    public void createQuiz(String quizName, List<QuestionData> questions) {
        addQuiz();
        AddQuizPage addQuizPage = new AddQuizPage(driver, wait, actions);
        addQuizPage.setTitle(quizName);
        for (QuestionData question : questions) {
            addQuizPage.createQuestion(question);
        }
        addQuizPage.saveQuiz();
    }

    public void createSingleQuiz(String quizName, QuestionData question) {
        addQuiz();
        AddQuizPage addQuizPage = new AddQuizPage(driver, wait, actions);
        addQuizPage.setTitle(quizName);
            addQuizPage.createQuestionWithTimeCheck(question);
        addQuizPage.clickOnlySaveButton();
    }
}
