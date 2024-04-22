package miniProject;

import java.util.*;

class GameController {
    private GameService gameService; // 게임 로직 관리 객체
    private GameView gameView; // 게임 화면 출력 객체
    private User currentUser; // 현재 로그인한 사용자 객체

    public void startGame(String userId) {
        currentUser = gameService.startGame(userId); // 게임 시작 및 현재 사용자 설정
        // 게임 진행 로직 구현
    }

    // 사용자가 입력한 정답. 현재 이미지 데이터.
    private void processAnswer(String userAnswer, ImageData imageData) {
        boolean isCorrect = gameService.checkAnswer(userAnswer, imageData);// 정답 확인
        if (isCorrect) {
            gameService.updateScore(currentUser, 1); // 정답일 경우 사용자 점수 업데이트
            gameView.showCorrectMessage();// 정답 메시지 출력
        } else {
            gameView.showIncorrectMessage();// 오답 메시지 출력
        }
    }

    
    //랭킹 출력
    public void showRanking() {
        List<User> ranking = gameService.getRanking();// 랭킹 정보 가져오기
        gameView.showRanking(ranking); // 랭킹 출력
    }
}