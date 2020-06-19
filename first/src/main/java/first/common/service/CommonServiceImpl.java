package first.common.service;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import first.common.dao.CommonDAO;

@Service("commonService")
public class CommonServiceImpl implements CommonService{
	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

	@Resource(name="commonDAO")
	private CommonDAO commonDAO;

	
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return commonDAO.selectFileInfo( map);
	}
	
	
}
