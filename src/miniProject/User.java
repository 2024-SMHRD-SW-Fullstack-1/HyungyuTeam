package miniProject;

// 사용자 정보를 나타내는 클래스
public class User {
	private String id; // 사용자 아이디
	private int score; // 사용자 점수
	
// 유저 get set 세팅하고 생성자 생성.
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	// 생성자 
	
	public User(String id, int score) {
		super();
		this.id = id;
		this.score = score;
	}
	
	
	
	
}