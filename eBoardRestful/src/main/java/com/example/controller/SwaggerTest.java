package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/test")
public class SwaggerTest {
	
	@Operation(summary = "테스트 api", description = "여기는 설명부분입니다", tags = {" 테스트 "})
	@GetMapping("/hello")
	public String hello() {
		return "헬로우 안녕하세요";
	}
	
	@Operation(summary = "테스트 api2", description = "여기는 설명부분입니다2", tags = {" 테스트2 "})
	@PostMapping("/insert")
	public String insert() {
		return "데이터를 입력합니다";
	}
}
