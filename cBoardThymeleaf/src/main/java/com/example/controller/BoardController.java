package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.BoardVO;
import com.example.service.BoardService;

@Controller
@RequestMapping("board") //board라는 이름을 찾으면 이 컨트롤러를 탈거야
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired //dependency injection
	private BoardService service;
	
	@RequestMapping("/getBoardList") //getBoardList라는 요청이 들어오면 이 함수가 불려짐 ,@RequestMapping은 get/post mapping 둘다 인지함, url통해서 들어온 애는 getmapping으로 
	public void getBoardList(Model m) {
		logger.info("[BoardController - getBoardList] 요청"); //syso안쓰고 이 안에 클래스명-메소드명
	    List<BoardVO> listBoard = service.getBoardList();

	    m.addAttribute("boardList", listBoard);
	}
	
//	@RequestMapping("/insertBoard") //얘가 있어야 화면에 뜨기 때문에
//	public String insertBoard() {
//		logger.info("[BoardController] 요청" + insertBoard); //개발자로서 얘네가 잘 불려지는지 확인만 할거야, 로그로 작성해야 로그 설정 파일을 나중에 건드릴 수 있다. 어떤 파일로 전송하는 한 줄로 기술하면 파일로 저장됨.
		
		@RequestMapping("/{step}") //얘가 있어야 화면에 뜨기 때문에, insertBoard 대신에 변수명으로 처리
		public String insertBoard(@PathVariable String step) {
			logger.info("[BoardController] 요청" + step); //개발자로서 얘네가 잘 불려지는지 확인만 할거야, 로그로 작성해야 로그 설정 파일을 나중에 건드릴 수 있다. 어떤 파일로 전송하는 한 줄로 기술하면 파일로 저장됨.
			return "board/" + step;
	}
	
	@RequestMapping("/saveBoard")
	public String saveBoard(BoardVO vo) { //화면으로부터 넘겨받아야 되어서, 리턴값이 문자열
		logger.info("[BoardController - saveBoard] 요청"); //개발자로서 얘네가 잘 불려지는지 확인만 할거야, 로그로 작성해야 로그 설정 파일을 나중에 건드릴 수 있다. 어떤 파일로 전송하는 한 줄로 기술하면 파일로 저장됨.
		service.saveBoard(vo); //vo만 넘겨줘
		return "redirect:getBoardList";
	}
	
	@RequestMapping("/getBoard")
	public void getBoard(BoardVO vo, Model m) {
		logger.info("[BoardController - getBoard] 요청"); //개발자로서 얘네가 잘 불려지는지 확인만 할거야, 로그로 작성해야 로그 설정 파일을 나중에 건드릴 수 있다. 어떤 파일로 전송하는 한 줄로 기술하면 파일로 저장됨.
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
