package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.BoardVO;

@RestController
public class SampleExample {

	@GetMapping("/hello")
	public String hello() {
		return "안녕 부트";
	}
	
	@GetMapping("/hello2")
	public BoardVO hello2() {
		BoardVO vo = new BoardVO();		
		vo.setSeq(1004);
		vo.setTitle("제목");
		vo.setContent("글내용");
		
		return vo;
	}
}
