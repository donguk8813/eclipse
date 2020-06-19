package first.sample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import first.common.dao.AbstractDAO;


@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO{

	//게시글
	
	//게시글 목록 조회
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) {
		return (List<Map<String, Object>>)selectPagingList("sample.selectBoardList", map);
	}
	
	//게시글 세부정보 조회
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception{
		return (Map<String, Object>)selectOne("sample.selectBoardDetail", map);
	}
	
	//게시글 추가
	public void insertBoard(Map<String, Object> map) throws Exception{
		insert("sample.insertBoard", map);
	}

	//게시글 조회수 증가
	public void updateHitCnt(Map<String, Object> map) throws Exception{
		update("sample.updateHitCnt", map);
	}
	
	//게시글 수정
	public void updateBoard(Map<String, Object> map) throws Exception{
		update("sample.updateBoard",map);
	}
	
	//게시글 삭제
	public void deleteBoard(Map<String, Object> map) throws Exception{
		update("sample.deleteBoard", map);
	}
	
	
	
	//첨부 파일
	
	//첨부 파일 목록 조회
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFileList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("sample.selectFileList", map);
	}
	
	//첨부 파일 추가
	public void insertFile(Map<String, Object> map) throws Exception{
		insert("sample.insertFile", map);
	}
	
	//첨부 파일 수정
	public void updateFile(Map<String, Object> map) throws Exception{
		update("sample.updateFile", map);
	}
	
	//첨부 파일 삭제
	public void deleteFileList(Map<String, Object> map) throws Exception{
		update("sample.deleteFileList", map);
	}
}
