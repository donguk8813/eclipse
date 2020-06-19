package first.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils {
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	private static final String filePath = "C:\\GDDEV\\file\\";

	public List<Map<String, Object>> parseUpdateFileInfo(Map<String, Object> map, HttpServletRequest request)
			throws Exception {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;

		String boardIdx = (String) map.get("IDX");
		String requestName = null;
		String idx = null;

		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if (multipartFile.isEmpty() == false) {
				originalFileName  = multipartFile.getOriginalFilename();

				// 파일의 원본이름을 받아와서 해당 파일의 확장자를 얻어냄
				originalFileExtension  = originalFileName.substring(originalFileName.lastIndexOf("."));

				// 32자리의 랜덤한 파일이름 생성 후 원본파일의 확장자를 붙여서 새로운 파일명을 만듬
				storedFileName  = CommonUtils.getRandomString() + originalFileExtension;

				// 지정된 경로에 파일을 생성
				multipartFile.transferTo(new File(filePath + storedFileName));

				listMap = new HashMap<String, Object>();
				listMap.put("IS_NEW", "Y");
				listMap.put("BOARD_IDX", boardIdx);
				listMap.put("ORIGINAL_FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				list.add(listMap);

				logger.debug("------------- file start -------------");
				logger.debug("name : " + multipartFile.getName());
				logger.debug("filename : " + multipartFile.getOriginalFilename());
				logger.debug("size : " + multipartFile.getSize());
				logger.debug("-------------- file end --------------\n");

			} else {
				requestName = multipartFile.getName();
				idx = "IDX_" + requestName.substring(requestName.indexOf("_") + 1);
				if (map.containsKey(idx) == true && map.get(idx) != null) {
					listMap = new HashMap<String, Object>();
					listMap.put("IS_NEW", "N");
					listMap.put("FILE_IDX", map.get(idx));
					list.add(listMap);
				}
			}
		}

		return list;
	}
}
