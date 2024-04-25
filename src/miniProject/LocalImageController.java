package miniProject;

import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

public class LocalImageController {
    private LocalImageRepository view;
    private UserRepository userRepository;
    private int score = 0;
    private String[][] QUIZ_DATA;
    private int currentQuizIndex = 0;
    private static final String IMAGE_FOLDER_PATH = "C:\\java_workspace\\ImageQuizGame\\image\\";
    private String[] IMAGE_FILE_NAMES;

    public LocalImageController(LocalImageRepository view, UserRepository userRepository) {
        this.view = view;
        this.userRepository = userRepository; // userRepository를 생성자 매개변수로 받아 초기화
    }

    public void startGame(int quizCount) {
        // 이미지 파일 이름과 문제 내용을 초기화
        IMAGE_FILE_NAMES = getImageFileNames();
        QUIZ_DATA = new String[quizCount][2];
        for (int i = 0; i < quizCount && i < IMAGE_FILE_NAMES.length; i++) {
            QUIZ_DATA[i][0] = "이게 머게ㅎㅎ" + (i + 1);
            QUIZ_DATA[i][1] = getImageNameWithoutExtension(IMAGE_FILE_NAMES[i]);
        }
        displayNextQuiz();
    }

    private String[] getImageFileNames() {
        File imageFolder = new File(IMAGE_FOLDER_PATH);
        String[] fileNames = imageFolder.list();
        return fileNames != null ? fileNames : new String[0];
    }

    private String getImageNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            return fileName.substring(0, dotIndex);
        }
        return fileName;
    }

    private void displayNextQuiz() {
        if (currentQuizIndex < QUIZ_DATA.length) {
            String[] currentQuiz = QUIZ_DATA[currentQuizIndex];
            String question = currentQuiz[0];
            ImageIcon icon = new ImageIcon(IMAGE_FOLDER_PATH + IMAGE_FILE_NAMES[currentQuizIndex]);
            view.setImageIcon(icon);
            view.setQuestionText(question); // UI 스레드에서 직접 호출
        } else {
            endGame(); // 퀴즈가 마지막인 경우 게임 종료
        }
    }

    public void handleAnswerSubmit() {
        String[] currentQuiz = QUIZ_DATA[currentQuizIndex];
        String userAnswer = view.getAnswerText();
        String correctAnswer = currentQuiz[1].toLowerCase();

        if (userAnswer.equals(correctAnswer)) {
            view.showCorrectMessage();
            score++;
            view.updateScore(score);
        } else {
            view.showIncorrectMessage(correctAnswer);
        }

        currentQuizIndex++;
        displayNextQuiz(); // 다음 퀴즈 표시
    }

    private void endGame() {
        String nickname = view.getPlayerNickname();
        if (nickname != null && !nickname.isEmpty()) {
            view.showFinalScore(score);
            saveScoreToDatabase(nickname, score);
            List<User> allUsers = userRepository.getAllUsers();
            view.showAllUsersScores(allUsers);
        }
    }

    private void saveScoreToDatabase(String nickname, int score) {
        User user = userRepository.getUserByUserId(nickname);
        if (user == null) {
            user = new User(nickname, score);
            userRepository.saveUser(user);
        } else {
            userRepository.updateUserScore(user, score);
        }
    }
}