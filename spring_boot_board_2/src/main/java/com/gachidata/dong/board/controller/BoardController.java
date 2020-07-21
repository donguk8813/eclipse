package com.gachidata.dong.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.gachidata.dong.board.domain.BoardVO;
import com.gachidata.dong.board.domain.FileVO;
import com.gachidata.dong.board.service.BoardService;


@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Value("${file.upload.directory}")
	private  String uploadFileDir;
	
	@Resource(name="com.gachidata.dong.board.service.BoardService")
	BoardService boardService;
	
	@GetMapping("/list")
	public String boardList(Model model) throws Exception{
		model.addAttribute("list", boardService.boardList());
		
		return "list";
	}
	
	@RequestMapping("/detail/{bno}")
	public String boardDetail(@PathVariable int bno, Model model) throws Exception{
		model.addAttribute("detail", boardService.boardDetail(bno));
		model.addAttribute("files", boardService.fileDetail(bno));
		logger.info("file : {} {}",boardService.fileDetail(bno), bno);
		return "detail";
	}
	
	@RequestMapping("/insert")
	public String boardInsertForm() {
		return "insert";
	}
	
	@RequestMapping("/insertProc")
	public String boardInsertProc(HttpServletRequest req, @RequestPart MultipartFile files) throws Exception{
		BoardVO board = new BoardVO();
		FileVO file = new FileVO();
		
		board.setSubject(req.getParameter("subject"));
		board.setContent(req.getParameter("content"));
		board.setWriter(req.getParameter("writer"));
		
		if(files.isEmpty()) {
			boardService.boardInsert(board);
		}else {
			String fileName = files.getOriginalFilename();
			String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
			File destinationFile;
			String destinationFileName;
			String fileUrl = "C:/GDDEV/workspace/spring_boot_board/src/main/webapp/WEB-INF/uploadFiles/";
			
			logger.info("file : {}",uploadFileDir);
			
			do {
				destinationFileName = RandomStringUtils.randomAlphanumeric(32)+"."+fileNameExtension;
				destinationFile = new File(fileUrl+destinationFileName);
//				destinationFile = new File(uploadFileDir);
			}while(destinationFile.exists());
			
			destinationFile.getParentFile().mkdirs();
			files.transferTo(destinationFile);
			
			boardService.boardInsert(board);
			
			
			
			file.setBno(board.getBno());
			file.setFile_name(destinationFileName);
			file.setFile_oriname(fileName);
			file.setFile_url(fileUrl);
//			file.setFile_url(uploadFileDir);
			
			boardService.fileInsert(file);
		}
		
				
		return "redirect:/list";
	}
	
	@RequestMapping("/update/{bno}")
	public String boardUpdateForm(@PathVariable int bno, Model model) throws Exception {
		model.addAttribute("detail", boardService.boardDetail(bno));
		return "update";
	}
	
	@RequestMapping("/updateProc")
	public String boardUpdateProc(HttpServletRequest req) throws Exception{
		BoardVO board = new BoardVO();
		
		board.setSubject(req.getParameter("subject"));
		board.setContent(req.getParameter("content"));
		board.setBno(Integer.parseInt(req.getParameter("bno")));
		
		boardService.boardUpdate(board);
		
		return "redirect:/detail/"+req.getParameter("bno");
	}
	
	@RequestMapping("/delete/{bno}")
	public String boardDelete(@PathVariable int bno) throws Exception{
		boardService.boardDelete(bno);
		return "redirect:/list";
	}
		
	@RequestMapping("/fileDown/{bno}")
	public void fileDown(@PathVariable int bno, HttpServletRequest req, HttpServletResponse resp) throws Exception{
		req.setCharacterEncoding("UTF-8");
		FileVO fileVO = boardService.fileDetail(bno);
		
		//파일 업로드 경로
		try {
			String fileUrl = fileVO.getFile_url();
			fileUrl += "/";
			String savePath = fileUrl;
			String fileName = fileVO.getFile_name();
			
			//실제 내보낼 파일명
			String oriFileName = fileVO.getFile_oriname();
			InputStream in = null;
			OutputStream os = null;
			File file = null;
			boolean skip = false;
			String client = "";
			
			//파일을 읽어 스트림에 담기
			try {
				file = new File(savePath, fileName);
				in = new FileInputStream(file);
			}catch(FileNotFoundException e) {
				skip = true;
			}
			
			client = req.getHeader("User-Agent");
			
			//파일 다운로드 헤더 지정
			resp.reset();
			resp.setContentType("application/octest-stream");
			resp.setHeader("Content-Description", "JSP Generated Data");
			
			if(!skip) {
				//IE
				if(client.indexOf("MSIE") != -1) {
					resp.setHeader("Content-Dispostion", "attachment; file_name=\""
							+ java.net.URLEncoder.encode(oriFileName, "UTF-8").replaceAll("\\+", "\\ ")+"\"");
				}else if(client.indexOf("Trident") != -1) {
					resp.setHeader("Content-Disposition", "attachment; file_name=\""
							+java.net.URLEncoder.encode(oriFileName, "UTF-8").replace("\\+","\\ ")+"\"");
				}else {
					//한글 파일명 처리
					resp.setHeader("Content-Disposition", "attachment; file_name=\""
							+new String(oriFileName.getBytes("UTF-8"),"ISO8859_1")+"\"");
					resp.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
				}
				resp.setHeader("Content-Length", ""+file.length());
				os = resp.getOutputStream();
				byte b[] = new byte[(int)file.length()];
				int leng = 0;
				while((leng = in.read(b))>0) {
					os.write(b,0,leng);
				}
			}else {
				resp.setContentType("text/html;charset=UTF-8");
				logger.info("<script language='javascript'>alert('파일을 찾을 수 없습니다.');history.back();</script>");
			}
			in.close();
			os.close();
		}catch(Exception e) {
			logger.info("ERROR : {}",e.getMessage());
		}
	}
	
}
