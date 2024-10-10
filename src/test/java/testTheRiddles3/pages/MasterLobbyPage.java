package testTheRiddles3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MasterLobbyPage extends PageTemplate {
    public MasterLobbyPage(String url, WebDriver driver, WebDriverWait wait, Actions actions) {
        super(url, driver, wait, actions);
    }


    @FindBy(xpath = "//button[text()='Start']]")
    private WebElement startBtn;
    @FindBy(xpath = "//button[text()='Create game lobby']]")
    private WebElement createLobbyBtn;
    @FindBy(xpath = "//button[text()='Results']]")
    private WebElement resultsBtn;

    @FindBy(xpath = "//div[contains(text()),'player']]")
    private WebElement players;

    @FindBy(xpath = "//div[contains(text()),'Questions']]")
    private WebElement questions;

    private static int extractInt(String text) {
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }

    private int getNumberOfPlayers() {
        String text = players.getText();
        return extractInt(text);
    }


    private int getNumberOfQuestions() {
        String text = questions.getText();
        return extractInt(text);
    }

    private void startGame() {
        startBtn.click();
    }

    private void createLobby() {
        createLobbyBtn.click();
    }
    private void clickResults() {
        resultsBtn.click();
    }


}
