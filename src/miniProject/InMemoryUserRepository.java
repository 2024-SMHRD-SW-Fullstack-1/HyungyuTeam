package miniProject;

import java.util.List;

// In-memory 데이터 저장소 사용
class InMemoryUserRepository implements UserRepository {

	@Override
	public User getUserByUserId(String userId) {
		// TODO 사용자 아이디로 사용자 정보를 메모리에서 가져오는 로직을 구현해야 함
		return null;
	}

	@Override
	public void saveUser(User user) {
		// TODO 사용자 정보를 메모리에 저장하는 로직을 구현해야 함
		
	}

	@Override
	public void updateUserScore(User user, int score) {
		// TODO 사용자 점수를 메모리에 업데이트하는 로직을 구현해야 함
		
	}

	@Override
	public List<User> getAllUsers() {
		// TODO 모든 사용자 정보를 메모리에서 가져오는 로직을 구현해야 함
		return null;
	}
	
	// 1. JDBC 드라이버 로드
	
	// 2. 데이터베이스 연결정보 설정
	
	// 3. 데이터베이스 연결 객체 설정 conn
	
    // In-memory 데이터 저장소 대신 데이터베이스 연결 객체를 사용하여 구현
}