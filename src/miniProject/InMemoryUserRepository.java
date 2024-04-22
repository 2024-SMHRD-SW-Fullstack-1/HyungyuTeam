package miniProject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// In-memory 데이터 저장소 사용
class InMemoryUserRepository implements UserRepository {
    private Map<String, User> users;

    public InMemoryUserRepository() {
        this.users = new HashMap<>();
    }

	@Override
	public User getUserByUserId(String userId) {
		// TODO 사용자 아이디로 사용자 정보를 메모리에서 가져오는 로직을 구현해야 함
		return null;
	} // 리턴값 적으면 됨.

	@Override
	public void saveUser(User user) {
		// TODO 사용자 정보를 메모리에 저장하는 로직을 구현해야 함
		// users에 뭐 집어넣을지 고민.
	}

	@Override
	public void updateUserScore(User user, int score) {
		// TODO 사용자 점수를 메모리에 업데이트하는 로직을 구현해야 함
		
	}// user 통해서 뭐 넣기.

	@Override
	public List<User> getAllUsers() {
		// TODO 모든 사용자 정보를 메모리에서 가져오는 로직을 구현해야 함
		return null;
	}// 리턴값 고민.
	
}