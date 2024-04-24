package miniProject;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;

public class LocalImageRepository extends JFrame {
    private JTextField textField;
    private JLabel scoreLabel;

    public LocalImageRepository() {
        super("이미지 퀴즈 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        // 이미지 레이블 생성
        JLabel imageLabel = new JLabel();
        panel.add(imageLabel, BorderLayout.CENTER);

        // 텍스트 필드 생성
        textField = new JTextField(20);
        panel.add(textField, BorderLayout.SOUTH);

        // 점수 레이블 생성
        scoreLabel = new JLabel("Score: 0");
        panel.add(scoreLabel, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setImageIcon(ImageIcon icon) {
        JLabel imageLabel = (JLabel) ((JPanel) getContentPane().getComponent(0)).getComponent(0);
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