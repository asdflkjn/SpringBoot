package com.example.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.BoardVO;
import com.example.domain.FileVO;
import com.example.service.BoardService;
import com.example.service.BoardServiceImpl;
import com.example.util.MD5Generator;

@Controller
@RequestMapping("board") 
public class BoardController {

    private final MemberController memberController;

    private final BoardServiceImpl boardServiceImpl;
	
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired 
	private BoardService service;

    BoardController(BoardServiceImpl boardServiceImpl, MemberController memberController) {
        this.boardServiceImpl = boardServiceImpl;
        this.memberController = memberController;
    }
	
	@RequestMapping("/getBoardList") 
	public void getBoardList(Model m) {
		logger.info("[BoardController - getBoardList] 요청");
	    List<BoardVO> listBoard = service.getBoardList();

	    m.addAttribute("boardList", listBoard);
	}
	
//	@RequestMapping("/insertBoard") 
//	public String insertBoard() {
//		logger.info("[BoardController] 요청" + insertBoard); 
		
		@RequestMapping("/{step}") 
		public String insertBoard(@PathVariable String step) {
			logger.info("[BoardController] 요청" + step); 
			return "board/" + step;
	}
	

/*		
	@RequestMapping("/saveBoard")
	public String saveBoard(BoardVO vo) {
		logger.info("[BoardController - saveBoard] 요청"); 

		// [예시] writer의 암호화
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // 암호화하는 알고리즘 처리
		logger.info("[암호화 하기 전] 작성자" + vo.getWriter());
		String secureWriter = encoder.encode(vo.getWriter());
		logger.info("[암호화 하기 후] 작성자" + secureWriter);
		
		//디비에 입력하기
		//vo.setWriter(secureWriter);
		service.saveBoard(vo); //vo만 넘겨줘
		
		//--------------------------------------------------------
		// [예시] securewriter : DB에서 가져온 값
		BCryptPasswordEncoder encoder2 = new BCryptPasswordEncoder(); 
		if(encoder2.matches(vo.getWriter(), secureWriter)) { //원래값하고 암호화한 거하고  매치한다.
			logger.info(">>>> 매치");
		}else {
			logger.info(">>>> 매치하지 않음");
		}
		
		return "redirect:getBoardList";
	}
*/
				
	// Fileupload 볼 때 , 2nd
	@RequestMapping("/saveBoard")
	public String saveBoard(BoardVO vo, @RequestParam("file") MultipartFile files) { // 앞에서 name이 file인 애를 이 클래스의 files로 받을거야
		logger.info("[BoardController - saveBoard] 요청"); 	
		
		try {
			// 파일의 원래 이름
			String originFilename = files.getOriginalFilename(); // 변수를 하나 준다.
//			logger.info("[[원래파일명]]" + originFilename);
			
			// 파일을 첨부한 경우
			if(originFilename != null && !originFilename.equals(""))
			{
				// 변경된 파일이름
				String filename = new MD5Generator(originFilename).toString();
//				logger.info("[[변경된 파일명]]" + filename);
				// #############[추후작업] 확장자 붙이기
				
				// 정해진 폴더를 지정 (절대경로 쓰면 안됨)
				String savepath = System.getProperty("user.dir");
				savepath+="\\src\\main\\resources\\static\\files";
//				logger.info("[[저장경로]]" + savepath);
				
				if(!new File(savepath).exists()) {
					new File(savepath).mkdir(); 
				}
				
				// 실제파일을 저장하고 디비에 파일정보를 입력하기
				String filepath = savepath + "\\" + filename;
				files.transferTo(new File(filepath)); //실제로 저장하는 애 , 실제로 업로딩되는 코딩 한줄
				logger.info("[[실제로 저장경로]]" + filepath);
				
				// 디비 저장
				FileVO fileVO = new FileVO();
				fileVO.setFilename(filename);
				fileVO.setOriginFilename(originFilename);
				fileVO.setFilepath(filepath); // 추후에 savepath로 넣어주는 것 권장
				
				boardServiceImpl.saveBoard(vo, fileVO); // 게시판에 들어가는 vo가 들어가서 입력하고, fileVO가 들어가서 실제적으로 입력
				
			}else {
			// 파일을 첨부하지 않은 경우
				boardServiceImpl.saveBoard(vo, null);
			}
						
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	return "redirect:getBoardList";		
	}
	
	
		
	@RequestMapping("/getBoard")
	public void getBoard(BoardVO vo, Model m) {
		logger.info("[BoardController - getBoard] 요청"); 
	    HashMap result = service.getBoard(vo);
	    m.addAttribute("board", result);
	}
	
	@RequestMapping("/updateBoard")
	public String updateBoard(BoardVO vo) {
	    logger.info("[BoardController - updateBoard] 요청");
	    service.updateBoard(vo);
	    return "redirect:getBoardList";
	}

	@RequestMapping("/deleteBoard")
	public String deleteBoard(BoardVO vo) {
	    logger.info("[BoardController - deleteBoard] 요청");
	    // 게시글 삭제 처리
	    service.deleteBoard(vo);
	    return "redirect:getBoardList";
	}
}
