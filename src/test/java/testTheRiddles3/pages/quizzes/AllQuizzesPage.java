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

import java.util.List;

public class AllQuizzesPage extends PageTemplate {
    @FindBy(xpath = "//div[.//span and .//button[text()='Copy'] and .//button[text()='Play']]")
    private List<WebElement> quizElements;
    @FindBy(xpath = "//button[text()='Add Quiz']]")
    private WebElement addQuizButton;

    public AllQuizzesPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(URL.ALL_QUIZ_URL, driver, wait, actions);
    }

    public List<WebElement> getQuizzes() {
        return quizElements;
    }

    public WebElement getQuizByName(String quizName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text() = '%s']/..".formatted(quizName))));
        return driver.findElement(By.xpath("//span[text() = '%s']/..".formatted(quizName)));

    }


    public void copyQuiz(String quizName) throws org.openqa.selenium.NoSuchElementException {
        getQuizByName(quizName).findElement(By.xpath("//button[text()='Copy']")).click();
        wait.until(ExpectedConditions.urlContains("quizform"));
    }


}
