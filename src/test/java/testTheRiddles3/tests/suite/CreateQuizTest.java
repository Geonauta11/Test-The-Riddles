package testTheRiddles3.tests.suite;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;
import testTheRiddles3.URL;
import testTheRiddles3.macro.UserMacro;
import testTheRiddles3.pages.LoginPage;
import testTheRiddles3.pages.NavPage;
import testTheRiddles3.pages.SignUpPage;
import testTheRiddles3.pages.quizzes.*;
import testTheRiddles3.tests.TestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateQuizTest extends TestTemplate {
    UserMacro userMacro;
    MyQuizzesPage myQuizzesPage;

    @BeforeAll
    public void setUpCreateQuizClass() {
        userMacro = new UserMacro(driver, wait, actions);
        userMacro.createCredentialsFromTimestamp(timestamp);
        userMacro.register();
        userMacro.login();
    }

    @Test
    public void myQuizzesPage_createQuiz_quizAppears() {
        driver.get(URL.MY_QUIZZES_URL);
        myQuizzesPage = new MyQuizzesPage(driver, wait, actions);
        String quizName = "quiz%d".formatted(timestamp);
        myQuizzesPage.createQuiz(
            quizName,
            List.of(
                new QuestionData(
                    "question1",
                    "42",
                    List.of(
                        new AnswerData("answer1", true),
                        new AnswerData("answer2", false)
                        )),
                new QuestionData(
                    "question2",
                    "43",
                    List.of(
                        new AnswerData("answer3", false),
                        new AnswerData("answer4", true)
                    )
                ))
        );
        driver.get(URL.MY_QUIZZES_URL);
        Assertions.assertDoesNotThrow(() -> myQuizzesPage.editQuiz(quizName));
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class OneCorrectAnswerTest{
        AddQuizPage addQuizPage;
        String quizName;

        @BeforeAll
        public void setUpOneCorrectAnswerClass() {
            driver.get(URL.MY_QUIZZES_URL);
            MyQuizzesPage myQuizzesPage = new MyQuizzesPage(driver, wait, actions);
            myQuizzesPage.addQuiz();
            addQuizPage = new AddQuizPage(driver, wait, actions);
            quizName = "quiz%d".formatted(timestamp);
            addQuizPage.setTitle(quizName);

        }

        private List<AnswerData> buildAnswers(Boolean[] checkBoxValues) {
            AtomicInteger i = new AtomicInteger(0);
            return Arrays.stream(checkBoxValues)
                .map((b) -> new AnswerData("answer%d".formatted(i.getAndAdd(1)), b))
                .toList();

        }

        @ParameterizedTest
        @MethodSource("validCheckBoxValueProvider")
        public void addQuizPage_createQuestionWithOneCorrectAnswer_questionIsPresent(Boolean[] checkBoxValues) {
            String questionName = "question%d".formatted(timestamp);
            List<AnswerData> answers = buildAnswers(checkBoxValues);
            QuestionData questionData = new QuestionData(questionName, "30", answers);
            addQuizPage.createQuestion(questionData);
            Assertions.assertDoesNotThrow(() -> addQuizPage.editQuestion(questionName));
        }

        @ParameterizedTest
        @MethodSource("invalidCheckBoxValueProvider")
        public void addQuizPage_createQuestionWithInvalidAnswer_alertIsThrown(Boolean[] checkBoxValues) {
            String questionName = "question%d".formatted(timestamp);
            List<AnswerData> answers = buildAnswers(checkBoxValues);
            QuestionData questionData = new QuestionData(questionName, "30", answers);
            addQuizPage.createQuestion(questionData);
            Assertions.assertTrue(addQuizPage.oneCorrectAnswerAlertIsPresent());
        }

        private Stream<Arguments> validCheckBoxValueProvider() {
            return Stream.of(
                Arguments.of((Object) new Boolean[]{true, false}),
                Arguments.of((Object) new Boolean[]{false, true}),
                Arguments.of((Object) new Boolean[]{true, false, false}),
                Arguments.of((Object) new Boolean[]{false, true, false}),
                Arguments.of((Object) new Boolean[]{false, false, true})
            );
        }

        private Stream<Arguments> invalidCheckBoxValueProvider() {
            return Stream.of(
                Arguments.of((Object) new Boolean[]{true, true,}),
                Arguments.of((Object) new Boolean[]{false, false}),
                Arguments.of((Object) new Boolean[]{true, true, false}),
                Arguments.of((Object) new Boolean[]{false, true, true}),
                Arguments.of((Object) new Boolean[]{true, false, true}),
                Arguments.of((Object) new Boolean[]{false, false, false}),
                Arguments.of((Object) new Boolean[]{true, true, true})
            );
        }
    }
}
