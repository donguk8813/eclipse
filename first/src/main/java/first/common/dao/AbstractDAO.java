package first.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class AbstractDAO {
	private static final Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

	@Autowired
	private SqlSessionTemplate sqlSession;

	protected void printQueryId(String queryId) {
		if (logger.isDebugEnabled()) {
			logger.debug("\t QueryId \t : " + queryId);
		}
	}

	public Object insert(String queryId, Object params) {
		printQueryId(queryId);
		return sqlSession.insert(queryId, params);
	}

	public Object update(String queryId, Object params) {
		printQueryId(queryId);
		return sqlSession.update(queryId, params);
	}

	public Object delete(String queryId, Object params) {
		printQueryId(queryId);
		return sqlSession.delete(queryId, params);
	}

	public Object selectOne(String queryId, Object params) {
		printQueryId(queryId);
		return sqlSession.selectOne(queryId, params);
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String queryId) {
		printQueryId(queryId);
		return sqlSession.selectList(queryId);
	}

	
	@SuppressWarnings("rawtypes")
	public List selectList(String queryId, Object params) {
		printQueryId(queryId);
		return sqlSession.selectList(queryId, params);
	}
	 
	@SuppressWarnings("unchecked")
	public Object selectPagingList(String queryId, Object params) {
		printQueryId(queryId);
		Map<String, Object> map = (Map<String, Object>) params;
		
		String strPageIndex = (String)map.get("PAGE_INDEX");
		String strPageRow = (String)map.get("PAGE_ROW");
		int nPageIndex = 0;
		int nPageRow = 20;
		
		if(StringUtils.isEmpty(strPageIndex) == false) {
			nPageIndex = Integer.parseInt(strPageIndex)-1;
		}
		if(StringUtils.isEmpty(strPageRow) == false ) {
			nPageRow = Integer.parseInt(strPageRow);
		}
		map.put("START", (nPageIndex * nPageRow) +1);
		map.put("END", (nPageIndex * nPageRow) + nPageRow);
		
		return sqlSession.selectList(queryId, map);
		
	}
	
	
	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map selectPagingList(String queryId, Object params) {
		printQueryId(queryId);

		Map<String, Object> map = (Map<String, Object>) params;
		PaginationInfo paginationInfo = null;

		if (map.containsKey("currentPageNo") == false
				|| StringUtils.isEmpty((String) map.get("currentPageNo")) == true) {
			map.put("currentPageNo", "1");
		}

		paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(Integer.parseInt(map.get("currentPageNo").toString()));

		logger.debug("밖 : {}",paginationInfo.getCurrentPageNo());
		if (map.containsKey("PAGE_ROW") == false || StringUtils.isEmpty((String) map.get("PAGE_ROW")) == true) {
			paginationInfo.setRecordCountPerPage(15);
			logger.debug("안");
		} else {
			paginationInfo.setRecordCountPerPage(Integer.parseInt(map.get("PAGE_ROW").toString()));
		}

		paginationInfo.setPageSize(10);

		int start = paginationInfo.getFirstRecordIndex();
		int end = start + paginationInfo.getRecordCountPerPage();
		map.put("START", start + 1);
		map.put("END", end);

		logger.debug("start: {} end: {}",start,end);

		params = map;

		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = sqlSession.selectList(queryId, params);

		if (list.size() == 0) {
			map = new HashMap<String, Object>();
			map.put("TOTAL_COUNT", 0);
			list.add(map);

			if (paginationInfo != null) {
				paginationInfo.setTotalRecordCount(0);
				returnMap.put("paginationInfo", paginationInfo);
			}
		} else {
			if (paginationInfo != null) {
				paginationInfo.setTotalRecordCount(Integer.parseInt(list.get(0).get("TOTAL_COUNT").toString()));
				returnMap.put("paginationInfo", paginationInfo);
			}
		}

		logger.debug("paginationInfo :  {}",paginationInfo.getTotalRecordCount());
		returnMap.put("result", list);
		return returnMap;
	}
	*/
}
