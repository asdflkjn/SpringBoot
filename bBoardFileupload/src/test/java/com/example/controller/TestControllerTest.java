package com.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/*
  JUnit: 자바에서 테스팅하는 프레임워크
  		- 단위테스트
  		- 통합테스트
  별도의 서버 동작없이 모조품: Mock
  
  - Junit 테스트 메소드 규칙
  		` public
  		` void
  		` 매개변수 없음
  		` @Test
 */

@WebMvcTest(TestController.class)
public class TestControllerTest {

	@Autowired // 알아서 넣어줘
	private MockMvc mockMvc;
	
	
	@Test
	public void test() throws Exception{
		mockMvc.perform(get("/test.do") //get방식으로 test.do를 불러보자
				.param("number", "1004")
				.param("number2", "1234"))
//				.andDo(print()) //실제로 구동했으면 이러이러했을거야.
				.andExpect(status().isOk()); //갔다왔을 때 상태값을 isOk로 불러본다.
				
	}
	
	
	@Test
	public void test2() throws Exception{
		mockMvc.perform(get("/happyPage.do")
				.param("name", "홍길동"))
//				.andDo(print()) // 이 요청에 name이 홍길동이 잘 들어가는 지만 확인하고 싶어 1st
				.andExpect(status().isOk()) //2nd
				.andExpect(view().name("happyPage")) //3rd , run as 돌렸을 때 이 페이지.jsp로 가는 거 맞니?
				.andExpect(model().attribute("greeting", "홍길동님 반갑습니다")) //4th
				;
	}
	
}
