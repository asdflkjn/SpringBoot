package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.BoardVO;
import com.example.persistence.QueryAnnoRepository;

@Controller
public class QueryAnnoController {

	@Autowired
	private QueryAnnoRepository repo;
	
	@RequestMapping("/queryanno")
	public String sample(Model m) {
		
		List<BoardVO> result = repo.queryAnno("홍");
		
		for(BoardVO vo : result) {
			System.out.println(">>" + vo.toString());
		}
		
		m.addAttribute("boardList", result);
		return "board/getBoardList";
		
	}
}
