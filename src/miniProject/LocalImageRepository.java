package miniProject;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocalImageRepository extends JFrame {
    private JTextField textField;
    private JLabel scoreLabel;
    private JButton startButton;
    private JComboBox<String> quizNumberComboBox;
    private LocalImageController controller;
    private UserRepository userRepository;
    private JLabel imageLabel;

    public LocalImageRepository(UserRepository userRepository) {
        super("이미지 퀴즈 게임");
        this.userRepository = userRepository;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        controller = new LocalImageController(this, userRepository);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleAnswerSubmit();
            }
        });

        scoreLabel = new JLabel("Score: 0");
        panel.add(scoreLabel, BorderLayout.SOUTH);

        imageLabel = new JLabel();
        panel.add(imageLabel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JTextField chatField = new JTextField(20);
        bottomPanel.add(chatField);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        setSize(750, 500);
        setLocationRelativeTo(null);
        setVisible(true);
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
        textField.setText("");
        JOptionPane.showMessageDialog(this, question, "퀴즈", JOptionPane.QUESTION_MESSAGE);
    }

    public String getAnswerText() {
        return textField.getText().trim().toLowerCase();
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
}