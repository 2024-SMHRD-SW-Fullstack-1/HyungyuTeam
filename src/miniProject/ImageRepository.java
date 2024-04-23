package miniProject;

import java.util.List;

/**
 * 이미지 데이터에 접근하고 관리하는 인터페이스
 */
interface ImageRepository {
    List<ImageData> getImages(); // 모든 이미지 데이터 가져오기
    ImageData getRandomImage(); // 랜덤 이미지 데이터 가져오기
}