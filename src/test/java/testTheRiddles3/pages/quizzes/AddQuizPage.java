package testTheRiddles3.pages.quizzes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testTheRiddles3.URL;
import testTheRiddles3.pages.PageTemplate;

public class AddQuizPage extends PageTemplate {
    @FindBy(xpath = "//button[contains(text(), 'Add Question')]")
    private WebElement addButton;

    @FindBy(id = "name")
    private WebElement titleInput;
    @FindBy(xpath = "//button[contains(text(), 'Save quiz')]")
    private WebElement saveButton;
    @FindBy(xpath = "//button[contains(text(), 'Delete quiz')]")
    private WebElement deleteButton;

    private String questionXpath = "//button[contains(text()[3], '%s')]";

    public AddQuizPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(URL.QUIZ_URL, driver, wait, actions);
    }

    public WebElement getQuestion(String name) {
        return driver.findElement(By.xpath(questionXpath.formatted(name)));
    }

    public void addQuestion() {
        click(addButton);
    }

    public void setTitle(String title) {
        setInput(titleInput, title);
    }

    public void saveQuiz() {
        click(saveButton);
        acceptAlert();
    }

    public void clickOnlySaveButton(){
        click(saveButton);
    }

    public void deleteQuiz() {
        deleteButton.click();
        acceptAlert();
    }

    public void editQuestion(String questionName) {
        click(getQuestion(questionName));
    }

    public void createQuiz(String title){
        setTitle(title);
        addQuestion();
        AddQuestionPage addQuestionPage = new AddQuestionPage(driver, wait, actions);
        addQuestionPage.setTitle("TestQuestion-1");
        addQuestionPage.setTimeLimit("50");
        addQuestionPage.setOption(1, "TestOption-1", true);
        addQuestionPage.setOption(2, "TestOption-2", false);
        addQuestionPage.saveQuestion();
        saveQuiz();
        driver.get(URL.MY_QUIZZES_URL);
    }

    public void createQuestion(QuestionData question) {
        addQuestion();
        AddQuestionPage addQuestionPage = new AddQuestionPage(driver, wait, actions);
        addQuestionPage.setTitle(question.questionName());
        addQuestionPage.setTimeLimit(question.timeLimit());
        for(int i=0; i<question.answers().size(); i++) {
            if (i>=2) {
                addQuestionPage.addOption();
            }
            AnswerData answer = question.answers().get(i);
            addQuestionPage.setOption(i+1, answer.text(), answer.isCorrect());
        }
        addQuestionPage.saveQuestion();
    }

    public void createQuestionWithTimeCheck(QuestionData question) {
        addQuestion();
        AddQuestionPage addQuestionPage = new AddQuestionPage(driver, wait, actions);
        addQuestionPage.setTitle(question.questionName());
        addQuestionPage.setTimeLimit(question.timeLimit());
        for(int i=0; i<question.answers().size(); i++) {
            if (i>=2) {
                addQuestionPage.addOption();
            }
            AnswerData answer = question.answers().get(i);
            addQuestionPage.setOption(i+1, answer.text(), answer.isCorrect());
        }
    }

    public boolean oneCorrectAnswerAlertIsPresent() {
        return driver.switchTo().alert().getText().equals("Exactly one answer has to be be correct");
    }
}
