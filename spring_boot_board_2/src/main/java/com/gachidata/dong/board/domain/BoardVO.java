package com.gachidata.dong.board.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class  BoardVO{
	
	private int bno;
	private String subject;
	private String content;
	private String writer;
	private Date reg_date;
	
}
