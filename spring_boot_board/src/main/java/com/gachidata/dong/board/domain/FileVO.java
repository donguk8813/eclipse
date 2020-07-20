package com.gachidata.dong.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FileVO {
	private int fno;
	private int bno;
	private String file_name;		//저장할 파일
	private String file_oriname;	//실제 파일
	private String file_url;		//db랑 연결할 때 이름이 같아야 되는데 대소문자는 판단 x (url Url 둘다됨)
	
}
