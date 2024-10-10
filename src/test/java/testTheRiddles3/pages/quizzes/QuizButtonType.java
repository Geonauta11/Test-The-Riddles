package testTheRiddles3.pages.quizzes;

public enum QuizButtonType {
    DELETE("Delete"),
    EDIT("Edit"),
    PLAY("Play");
    public final String buttonText;
    QuizButtonType(String buttonText) {
        this.buttonText = buttonText;
    }

}
