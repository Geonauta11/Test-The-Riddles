package testTheRiddles3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testTheRiddles3.URL;

public class GamesPage extends PageTemplate{
    private String joinXpath = "//span[text() = '%s'/following-sibling::button[text() = 'Join']]";

    public GamesPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(URL.GAMES_URL, driver, wait, actions);
    }

    public void join(String quizName) {
        WebElement joinButton = driver.findElement(By.xpath(joinXpath.formatted(quizName)));
        click(joinButton);
    }

}
