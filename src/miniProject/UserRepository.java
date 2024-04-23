package miniProject;

import java.util.*;

//* 사용자 데이터에 접근하고 관리하는 인터페이스

interface UserRepository {
    User getUserByUserId(String userId); // 사용자 아이디로 사용자 정보 가져오기
    void saveUser(User user); // 사용자 정보 저장
    void updateUserScore(User user, int score);// 사용자 점수 업데이트
    List<User> getAllUsers(); // 모든 사용자 정보 가져오기
    
}