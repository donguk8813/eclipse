package first.common.util;

import java.util.UUID;

public class CommonUtils {
	/**
	 DB를 생성할 때 서버에 저장될 파일명은 32글자 
	 getRandomString() 메서드는 32글자의 랜덤한 문자열(숫자포함)을 만들어서 반환
	 */
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-","");
	}
}
