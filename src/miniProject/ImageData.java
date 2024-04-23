package miniProject;

// 이미지 데이터를 나타내는 테이블
class ImageData {
    private String imagePath;  // 이미지 파일 경로
    private String answer; // 이미지에 대한 정답
    // Getters and Setters
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	
	// 생성자
	public ImageData(String imagePath, String answer) {
		super();
		this.imagePath = imagePath;
		this.answer = answer;
	}
    
    
    
    
    
    
    
    
    
}
