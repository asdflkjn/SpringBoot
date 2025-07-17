package com.example.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler implements ErrorController{

	@ExceptionHandler(Exception.class) //구체적인 Exception 클래스 지정
	public String handlerTest(Model model, Exception exception) {
		
		model.addAttribute("exception", exception);
		return "/error/errorPage";
	}
}
