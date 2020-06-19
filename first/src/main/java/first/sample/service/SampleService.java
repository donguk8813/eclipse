package first.sample.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SampleService {
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception; 
	
	public void insertBoard(Map<String, Object> map,HttpServletRequest request) throws Exception;
	
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception;
	
	public void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception; 
	
	public void deleteBoard(Map<String, Object> map) throws Exception;
	
}
