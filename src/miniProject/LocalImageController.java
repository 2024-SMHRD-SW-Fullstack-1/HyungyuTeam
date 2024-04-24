package miniProject;

import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

public class LocalImageController {
    private LocalImageRepository view;
    private UserRepository userRepository;
    private int score = 0;
    private static final String[][] QUIZ_DATA = {
            { "이게 머게ㅎㅎ", "" }, // 이미지 파일 이름은 이후 초기화됨
            { "이게 머게ㅎㅎ", "" },
            { "이게 머게ㅎㅎ", "" }
    };
    private int currentQuizIndex = 0;
    private static final String IMAGE_FOLDER_PATH = "C:/java_workspace/ImageQuizGame/image/";
    private static final String[] IMAGE_FILE_NAMES = getImageFileNames();

    private static String[] getImageFileNames() {
        File imageFolder = new File(IMAGE_FOLDER_PATH);
        String[] fileNames = imageFolder.list();
        return fileNames != null ? fileNames : new String[0];
    }

    public LocalImageController(LocalImageRepository view, UserRepository userRepository) {
        this.view = view;
        this.userRepository = userRepository;
        initGame();
    }

    private void initGame() {
        for (int i = 0; i < IMAGE_FILE_NAMES.length && i < QUIZ_DATA.length; i++) {
            QUIZ_DATA[i][1] = getImageNameWithoutExtension(IMAGE_FILE_NAMES[i]);
        }
        displayNextQuiz();
    }

    private String getImageNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            return fileName.substring(0, dotIndex);
        }
        return fileName;
    }

    private void displayNextQuiz() {
        String[] currentQuiz = QUIZ_DATA[currentQuizIndex];
        String question = currentQuiz[0];
        ImageIcon icon = new ImageIcon(IMAGE_FOLDER_PATH + IMAGE_FILE_NAMES[currentQuizIndex]);
        view.setImageIcon(icon);

        SwingUtilities.invokeLater(() -> view.setQuestionText(question));
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
        if (currentQuizIndex < QUIZ_DATA.length) {
            displayNextQuiz();
        } else {
            endGame();
        }
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