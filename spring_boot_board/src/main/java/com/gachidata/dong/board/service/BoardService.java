package com.gachidata.dong.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gachidata.dong.board.domain.BoardVO;
import com.gachidata.dong.board.domain.FileVO;
import com.gachidata.dong.board.mapper.BoardMapper;

@Service("com.gachidata.dong.board.service.BoardService")
public class BoardService {
	
	@Resource(name="com.gachidata.dong.board.mapper.BoardMapper")
	BoardMapper boardMapper;
	
	public List<BoardVO> boardList() throws Exception{
		return boardMapper.boardList();
	}
	
	public BoardVO boardDetail(int bno) throws Exception{
		return boardMapper.boardDetail(bno);
	}
	
	public int boardInsert(BoardVO boardVO) throws Exception{
		return boardMapper.boardInsert(boardVO);
	}
	
	public int fileInsert(FileVO fileVO) throws Exception{
		return boardMapper.fileInsert(fileVO);
	}
	
	public int boardUpdate(BoardVO boardVO) throws Exception{
		return boardMapper.boardUpdate(boardVO);
	}
	
	public int boardDelete(int bno) throws Exception{
		return boardMapper.boardDelete(bno);
	}
	
	public FileVO fileDetail(int bno) throws Exception{
		return boardMapper.fileDetail(bno);
	}
	
}
