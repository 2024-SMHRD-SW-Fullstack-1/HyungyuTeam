package miniProject;

import java.util.List;

// JDBC 사용해서 사용자 데이터 관리 구현
class JDBCUserRepository implements UserRepository {

	
	// 사용자 아이디로 사용자 정보 가져오기
	@Override
	public User getUserByUserId(String userId) {
		return null;
	}
	
    // 사용자 정보 저장
	@Override
	public void saveUser(User user) {		
	}

    // 사용자 점수 업데이트
	@Override
	public void updateUserScore(User user, int score) {
		// TODO Auto-generated method stub
		
	}

	// 모든 사용자 정보 가져오기
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
