package first.common.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import first.common.common.CommandMap;
import first.common.service.CommonService;

@Controller
public class CommonController {
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Resource(name="commonService")
	private CommonService commonService;
	
	@RequestMapping(value="/common/downloadFile.do")
	public void downloadFile(CommandMap commandMap, HttpServletResponse response) throws Exception{
		Map<String, Object> map = commonService.selectFileInfo(commandMap.getMap());
		String storedFileName = (String)map.get("STORED_FILE_NAME");
		String originalFileName = (String)map.get("ORIGINAL_FILE_NAME");
		
		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\GDDEV\\file\\"+storedFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		
		/*우리가 파일을 다운로드 받으려고 하면, 파일을 저장할 위치를 선택하는 창이 뜨고 파일의 이름이 지정되어있는데, 이부분이 그 역할
		 *중요한 점은 첨부 파일을 받을 때 UTF-8로 인코딩해야 파일명이 깨지지 않고 받을 수 있음.
		 *attachment; fileName=/""~~ 이 부분에서 세미콜론(;) 이후 띄어쓰기가 되어있는데 띄어쓰기를 안할 경우 다운로드 기능이 안될 수 있음(근데 됨..).
		 */
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}
}
