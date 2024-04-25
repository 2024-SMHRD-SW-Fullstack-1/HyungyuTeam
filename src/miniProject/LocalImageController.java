package miniProject;

import java.awt.Window;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class LocalImageController {
	private LocalImageRepository view;
	private UserRepository userRepository;
	private int score = 0;
	private String[][] QUIZ_DATA;
	private int currentQuizIndex = 0;
	private static final String IMAGE_FOLDER_PATH = "C:\\java_workspace\\ImageQuizGame\\image\\";
	private String[] IMAGE_FILE_NAMES;
	private List<Integer> usedImageIndices = new ArrayList<>();
	private JFrame frame;

	public LocalImageController(LocalImageRepository view, UserRepository userRepository) {
		this.view = view;
		this.userRepository = userRepository;

		// JFrame 생성 및 LocalImageRepository 추가
		frame = new JFrame("이미지 퀴즈 게임");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(view);
		frame.setSize(750, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// LocalImageRepository에 자신의 인스턴스를 전달
		view.setController(this);
	}

	private int selectRandomImageIndex() {
		int index;
		do {
			index = (int) (Math.random() * IMAGE_FILE_NAMES.length);
		} while (usedImageIndices.contains(index));
		usedImageIndices.add(index);
		return index;
	}

	public void startGame(int quizCount) {
		usedImageIndices.clear(); // 사용된 이미지 저장하는 인덱스 초기화
		IMAGE_FILE_NAMES = getImageFileNames(); // 이미지 폴더에서 이미지 파일의 이름 목록 가져옴
		QUIZ_DATA = new String[quizCount][2]; // 퀴즈데이터 배열 생성.
		for (int i = 0; i < quizCount && i < IMAGE_FILE_NAMES.length; i++) {
			QUIZ_DATA[i][0] = getImageNameWithoutExtension(IMAGE_FILE_NAMES[i]); // 이미지 파일의 이름을 문제로 설정
			QUIZ_DATA[i][1] = QUIZ_DATA[i][0]; // 이미지 파일의 이름을 정답으로 설정
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
		if (usedImageIndices.size() < QUIZ_DATA.length) {
			int imageIndex = selectRandomImageIndex(); // 랜덤 이미지 선택
			String[] currentQuiz = QUIZ_DATA[currentQuizIndex];
			String question = currentQuiz[0];
			String imageName = IMAGE_FILE_NAMES[imageIndex]; // 랜덤으로 선택한 이미지의 파일 이름
			String answer = getImageNameWithoutExtension(imageName); // 확장자 제거
			ImageIcon icon = new ImageIcon(IMAGE_FOLDER_PATH + imageName);
			view.setImageIcon(icon);
			view.setQuestionText(question);
			QUIZ_DATA[currentQuizIndex][1] = answer; // 정답을 퀴즈 데이터에 저장
		} else {
			endGame();
		}
	}

	public void handleAnswerSubmit() {
		boolean alreadyAnswered = currentQuizIndex >= QUIZ_DATA.length;

		if (!alreadyAnswered) {
			String[] currentQuiz = QUIZ_DATA[currentQuizIndex];
			String userAnswer = view.getAnswerText();
			String correctAnswer = currentQuiz[1].toLowerCase();
			if (userAnswer != null) {
				if (userAnswer.equals(correctAnswer)) {
					view.showCorrectMessage();
					score++;
					view.updateScore(score);
				} else {
					view.showIncorrectMessage(correctAnswer);
				}

				currentQuizIndex++;
				if (currentQuizIndex < QUIZ_DATA.length) {
					displayNextQuiz(); // 다음 퀴즈 표시
					view.clearAnswerField();
				} else {
					endGame(); // 모든 퀴즈가 종료된 경우 게임 종료
				}
			}
		}
	}

	private void endGame() {
		String nickname = view.getPlayerNickname();
		if (nickname != null && !nickname.isEmpty()) {
			view.showFinalScore(score);
			saveScoreToDatabase(nickname, score);
			List<User> allUsers = userRepository.getAllUsers();
			view.showAllUsersScores(allUsers);

			// 랭킹 표시 후 확인 버튼을 누르면 창을 닫습니다.
			int option = JOptionPane.showConfirmDialog(view, "게임을 종료하시겠습니까?", "게임 종료", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				// view가 유효한지 확인하고 창을 닫습니다.
				Window window = SwingUtilities.getWindowAncestor(view);
				if (window != null) {
					window.dispose();
				}
			}
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