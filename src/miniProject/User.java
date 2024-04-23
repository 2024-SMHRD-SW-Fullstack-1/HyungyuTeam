package miniProject;

// 사용자 정보를 나타내는 클래스
public class User {
	private String id; // 사용자 아이디
	private int score; // 사용자 점수
}
// 유저 get set 세팅하고 생성자 생성.

//생성자 
public User(String id, int score) {
	super();
	this.id = id;
	this.score = score;
}

//아이디 반환 메서드
public String getId() {
	return id;
}

//아이디 설정메서드
public void setId(String id) {
	this.id = id;
}
//점수 반환 메서드
public int getScore() {
	return score;
}
//점수 설정 메서드
public void setScore(int score) {
	this.score = score;
}
}