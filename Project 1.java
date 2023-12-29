import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Question class
class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswerIndex;

    public Question(String questionText, List<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean validateAnswer(int userAnswer) {
        return userAnswer == correctAnswerIndex;
    }
}

// Quiz class
class Quiz {
    private List<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

// User interface (QuizApp)
class QuizApp {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        
        // Adding questions to the quiz
        Question q1 = new Question("What is 5 + 7?", List.of("10", "11", "12", "13"), 2);
        Question q2 = new Question("Which planet is known as the Red Planet?", List.of("Mars", "Venus", "Jupiter", "Mercury"), 0);

        quiz.addQuestion(q1);
        quiz.addQuestion(q2);

        Scanner scanner = new Scanner(System.in);
        List<Question> questions = quiz.getQuestions();
        int score = 0;

        for (Question q : questions) {
            System.out.println(q.getQuestionText());
            List<String> options = q.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            System.out.print("Your answer: ");
            int userAnswer = scanner.nextInt();

            if (userAnswer >= 1 && userAnswer <= options.size()) {
                if (q.validateAnswer(userAnswer - 1)) {
                    score++;
                }
            } else {
                System.out.println("Invalid input. Skipping question.");
            }
        }

        System.out.println("Quiz completed! Your score is: " + score + "/" + questions.size());
    }
}
  