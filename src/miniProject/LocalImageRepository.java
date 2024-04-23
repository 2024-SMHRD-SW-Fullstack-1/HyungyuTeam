package miniProject;

import java.util.*;

// 로컬 폴더에서 이미지 데이터를 가져오는 ImageRepository 구현체

class LocalImageRepository implements ImageRepository {

	@Override
	public List<ImageData> getImages() {
		// TODO 로컬 폴더에서 모든 이미지 데이터를 가져오는 로직을 구현해야 함
		// 이미지 경로에서 파일명만 추출하고, 파일명에서 확장자 제외한 부분을 정답으로 처리할 예정.
		return null;
	}

	@Override
	public ImageData getRandomImage() {
		// TODO 로컬 폴더에서 랜덤 이미지 데이터를 가져오는 로직을 구현해야 함
		return null;
	}
   // 로컬 폴더에서 이미지 데이터 가져오기
}