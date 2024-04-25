// LocalImageRepository.java
package miniProject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.*;

public class LocalImageRepository extends JPanel {
	private JTextField textField;
	private JLabel scoreLabel;
	private JButton startButton;
	private JComboBox<String> quizNumberComboBox;
	private LocalImageController controller;
	private UserRepository userRepository;
	private JLabel imageLabel;
	private JTextField answerField;

	public LocalImageRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
		setLayout(new BorderLayout());
		scoreLabel = new JLabel("Score: 0");

		JPanel panel = new JPanel(new BorderLayout());
		add(panel);

		String[] quizOptions = { "10", "20" };
		quizNumberComboBox = new JComboBox<>(quizOptions);
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.add(new JLabel("퀴즈 수 선택:"));
		topPanel.add(quizNumberComboBox);
		startButton = new JButton("시작");
		topPanel.add(startButton);
		panel.add(topPanel, BorderLayout.NORTH);

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});

		textField = new JTextField(20);
		panel.add(textField, BorderLayout.CENTER);

		imageLabel = new JLabel();
		panel.add(imageLabel, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new FlowLayout());

		answerField = new JTextField(10);
		answerField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Not used
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					controller.handleAnswerSubmit();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// Not used
			}
		});
		bottomPanel.add(answerField);
		panel.add(bottomPanel, BorderLayout.SOUTH);
	}

	public void setController(LocalImageController controller) {
		this.controller = controller;
	}

	private void startGame() {
		String selectedOption = (String) quizNumberComboBox.getSelectedItem();
		int quizCount = Integer.parseInt(selectedOption);
		controller.startGame(quizCount);
	}

	public void setImageIcon(ImageIcon icon) {
		imageLabel.setIcon(icon);
	}

	public void setQuestionText(String question) {
		textField.setText(question);
	}

	public String getAnswerText() {
		return answerField.getText().trim().toLowerCase();
	}

	public void showCorrectMessage() {
		JOptionPane.showMessageDialog(this, "정답입니다!", "정답", JOptionPane.INFORMATION_MESSAGE);
	}

	public void showIncorrectMessage(String correctAnswer) {
		JOptionPane.showMessageDialog(this, "틀렸습니다. 정답은 " + correctAnswer + " 입니다.", "오답", JOptionPane.ERROR_MESSAGE);
	}

	public void updateScore(int score) {
		scoreLabel.setText("Score: " + score);
	}

	public String getPlayerNickname() {
		return JOptionPane.showInputDialog(this, "게임 종료! 닉네임을 입력하세요:", "게임 종료", JOptionPane.PLAIN_MESSAGE);
	}

	public void showFinalScore(int score) {
		JOptionPane.showMessageDialog(this, "게임 종료! 최종 점수: " + score, "게임 종료", JOptionPane.INFORMATION_MESSAGE);
	}

	public void showAllUsersScores(List<User> allUsers) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("===== 전체 사용자 점수 =====\n");
		for (User user : allUsers) {
			stringBuilder.append(user.getId()).append(": ").append(user.getScore()).append("점\n");
		}
		JOptionPane.showMessageDialog(this, stringBuilder.toString(), "전체 사용자 점수", JOptionPane.INFORMATION_MESSAGE);
	}

	public void clearAnswerField() {
		answerField.setText("");
	}
}