package testTheRiddles3.pages.quizzes;

import org.openqa.selenium.WebElement;

public record AnswerComponent(int index, WebElement input, WebElement checkBox, WebElement deleteButton) {
}
