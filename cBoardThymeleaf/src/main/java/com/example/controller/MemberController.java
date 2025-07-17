package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.MemberVO;
import com.example.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService service;

    @RequestMapping("/{step}")
    public String stepPage(@PathVariable String step) {
        logger.info("[MemberController] 동적 페이지 요청: " + step);
        return "member/" + step;
    }

    @RequestMapping("/saveMember")
    public String saveMember(MemberVO vo) {
        logger.info("[MemberController - saveMember] 요청");
        service.saveMember(vo);
        return "member/saveMember"; 
    }
  
	
	@RequestMapping("/loginMember")
	public String login(MemberVO vo, HttpSession session, Model m) {
		logger.info("[MemberController - loginMember] 요청");
	    MemberVO result = service.getMember(vo);
	    	session.setAttribute("login", result);
	    	return "member/loginSuccess";	    	
	}
    
	@RequestMapping("/logout")
    public String logout(HttpSession session) {
		logger.info("[MemberController - logoutMember] 요청");
		session.invalidate();
    	return "redirect:/member/loginForm";
    }
    
}
