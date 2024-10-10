package testTheRiddles3.pages.quizzes;

import java.util.List;

public record QuestionData(String questionName, String timeLimit, List<AnswerData> answers) {
}
