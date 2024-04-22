package miniProject;

import java.util.*;

// 게임 로직 구현부분
class GameService {
    private UserRepository userRepository;// 사용자 정보 관리 객체
    private ImageRepository imageRepository;// 이미지 데이터 관리 객체

    
    //생성자 만들어줘야함
    
    // 게임을 시작하고 사용자 객체를 반환
    public User startGame(String userId) {
        User user = userRepository.getUserByUserId(userId); // 사용자 정보 가져오기
        ImageData imageData = imageRepository.getRandomImage();	//랜덤 이미지 데이터 가져오기
        return user;
    }

    public boolean checkAnswer(String userAnswer, ImageData imageData) {
		return false;
        // 정답 확인 로직 구현
    }
    
    // 사용자 점수 업데이트. user = 사용자, score = 업데이트할 점수
    public void updateScore(User user, int score) {
        userRepository.updateUserScore(user, score); // 사용자 점수 업데이트
    }

    public List<User> getRanking() {
        List<User> users = userRepository.getAllUsers(); // 모든 사용자 정보 가져오기
        // 점수 기준으로 정렬
        return users;
    }
}
