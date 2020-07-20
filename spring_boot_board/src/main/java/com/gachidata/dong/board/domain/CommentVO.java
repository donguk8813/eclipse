package com.gachidata.dong.board.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentVO {
	private int cno;
	private int bno;
	private String content;
	private String writer;
	private Date reg_date;
	
}
