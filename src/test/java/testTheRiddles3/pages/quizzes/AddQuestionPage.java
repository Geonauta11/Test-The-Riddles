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

public class AddQuestionPage extends PageTemplate {
    @FindBy(xpath = "//label[contains(text(), 'Question')]/following-sibling::input")
    private WebElement titleInput;
    @FindBy(xpath = "//label[contains(text(), 'Time limit (seconds) ')]/following-sibling::input")
    private WebElement timeLimitInput;
    @FindBy(xpath = "//button[text() = '+ Add option']")
    private WebElement addOptionButton;
    @FindBy(xpath = "//button[text() = 'Save']")
    private WebElement saveButton;
    @FindBy(xpath = "//button[text() = 'Delete']")
    private WebElement deleteButton;

    public AddQuestionPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(URL.QUIZ_URL, driver, wait, actions);
    }

    private AnswerComponent getAnswer(int index) {
        String rowXpath = "//label[contains(text(), '%d.')]/../..".formatted(index);
        String answerInputXpath = rowXpath + "/div/input[@type='text']";
        String checkboxXpath = rowXpath + "/div/input[@type='checkbox']";
        String deleteXpath = rowXpath + "/div/button[text()='x']";
        WebElement xButton = findWithoutWait(By.xpath(deleteXpath)).orElse(null);
        return new AnswerComponent(
                index,
                driver.findElement(By.xpath(answerInputXpath)),
                driver.findElement(By.xpath(checkboxXpath)),
                xButton
        );

    }

    public void setTitle(String title) {
        setInput(titleInput, title);
    }

    public void setTimeLimit(String timeLimit) {
        timeLimitInput.clear();
        timeLimitInput.sendKeys(timeLimit);
    }

    public String getTimeLimit() {
        return timeLimitInput.getAttribute("value");
    }

    public void setOption(int index, String text, boolean correct) {
        AnswerComponent answer = getAnswer(index);
        setInput(answer.input(), text);
        if (correct != answer.checkBox().isSelected()) {
            click(answer.checkBox());
        }
    }

    public void addOption() {
        click(addOptionButton);
    }

    public void saveQuestion() {
        click(saveButton);
        acceptAlert();
    }

    public int getNumberAnswers() {
        return driver.findElements(By.xpath("//input[contains(@id, 'answer-')]")).size();
    }

    public void addNewOption(String text, boolean correct) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text() = '+ Add option']")));
        addOptionButton.click();
        setOption(getNumberAnswers(), text, correct);
        saveButton.click();
        acceptAlert();
    }
    public void addNewOption(String text, boolean correct, WebElement question){
        question.click();
        this.addNewOption(text, correct);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text()[3], 'TestQuestion-1')]")));
    }

    public void updateTimer(WebElement question){
        question.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Time limit (seconds) ')]/following-sibling::input")));
        this.setTimeLimit("180");
        this.saveQuestion();

    }

}
