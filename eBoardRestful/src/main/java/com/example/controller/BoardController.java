package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.BoardVO;
import com.example.service.BoardService;

@RestController //★★★★★★★★★ 비동기 방식, 100% ajax로 작업한 경우 사용
@RequestMapping("board") 
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired 
	private BoardService service;
	
	@GetMapping("/getBoardList") 
	public List<BoardVO> getBoardList() {
		logger.info("[BoardController - getBoardList] 요청");
	    List<BoardVO> listBoard = service.getBoardList();

	    return listBoard;
	}

	
	@PostMapping("/saveBoard")
	public void saveBoard(BoardVO vo) {
		logger.info("[BoardController - saveBoard] 요청"); 
		service.saveBoard(vo); 
	}
	
	@GetMapping("/getBoard")
	public BoardVO getBoard(BoardVO vo, Model m) {
		logger.info("[BoardController - getBoard] 요청"); 
	    BoardVO result = service.getBoard(vo);
	    
	    return result;
	}
	
//	@RequestMapping("/updateBoard")
//	public String updateBoard(BoardVO vo) {
//	    logger.info("[BoardController - updateBoard] 요청");
//	    service.updateBoard(vo);
//	    return "redirect:getBoardList";
//	}
//
//	@RequestMapping("/deleteBoard")
//	public String deleteBoard(BoardVO vo) {
//	    logger.info("[BoardController - deleteBoard] 요청");
//	    // 게시글 삭제 처리
//	    service.deleteBoard(vo);
//	    return 
//	}
}
