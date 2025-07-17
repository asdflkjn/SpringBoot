package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.BoardVO;
import com.example.service.BoardService;

@Controller
@RequestMapping("board") 
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired 
	private BoardService service;
	
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
	
	@RequestMapping("/getBoard")
	public void getBoard(BoardVO vo, Model m) {
		logger.info("[BoardController - getBoard] 요청"); 
	    BoardVO result = service.getBoard(vo);
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
