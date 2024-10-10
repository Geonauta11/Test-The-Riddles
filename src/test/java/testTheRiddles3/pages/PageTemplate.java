package testTheRiddles3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.html.Option;
import java.time.Duration;
import java.util.Optional;

public class PageTemplate {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public PageTemplate(String url, WebDriver driver, WebDriverWait wait, Actions actions) {
        wait.until(ExpectedConditions.urlContains(url));
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.scrollToElement(element).perform();
        element.click();
    }

    public Optional<WebElement> findWithoutWait(By locator) {
        Optional<WebElement> result;

        Duration oldWait = driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));
        try {
            WebElement element = driver.findElement(locator);
            result = Optional.of(element);
        } catch (Exception e) {
            result = Optional.empty();
        }
        driver.manage().timeouts().implicitlyWait(oldWait);
        return result;
    }

    protected void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    protected void setInput(WebElement element, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(text);
    }

    public boolean isUrlRight(String url){
        try {
            wait.until(ExpectedConditions.urlToBe(url));
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
