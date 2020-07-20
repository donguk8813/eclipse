package com.gachidata.dong.board.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
//@Alias("BoardVO")
@Setter
@Getter
public class BoardVO {
	
	private int bno;
	private String subject;
	private String content;
	private String writer;
	private Date reg_date;
	
}
