package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 동적 페이지 매핑
    @RequestMapping("/{step}")
    public String stepPage(@PathVariable String step) {
        logger.info("[MemberController] 동적 페이지 요청: " + step);
        return "member/" + step;
    }

    // 회원가입 처리
    @RequestMapping("/saveMember")
    public String saveMember(MemberVO vo) {
        logger.info("[MemberController - saveMember] 요청");

		// [예시] password의 암호화
		String rawPassword = vo.getPassword();
		logger.info("[암호화 하기 전] 패스워드" + rawPassword);
		
		String securePassword = encoder.encode(rawPassword);
		logger.info("[암호화 하기 후] 패스워드" + securePassword);
		
		//암호화된 비밀번호로 디비에 입력하기
		vo.setPassword(securePassword);
		service.saveMember(vo);
		
		return "member/saveMember"; 
	}
    
    
//	@RequestMapping("/loginMember")
//	public String login(MemberVO vo, HttpSession session, Model m) {
//		logger.info("[MemberController - loginMember] 요청");
//	    MemberVO result = service.getMember(vo);  // ID로 조회한 회원정보
//	    	session.setAttribute("login", result);
//	    	return "member/loginSuccess";	    	
//	}
    
    // 로그인 처리
    @RequestMapping("/loginMember")
    public String login(MemberVO vo, HttpSession session, Model model) {
        logger.info("[MemberController - loginMember] 요청");

        MemberVO dbMember = service.getMember(vo); // ID로 조회한 회원정보

        if (dbMember != null) {
            String rawPassword = vo.getPassword(); // 사용자가 입력한 비밀번호
            String encodedPassword = dbMember.getPassword(); // DB에 저장된 암호화된 비밀번호

            logger.info(encodedPassword);
            logger.info(rawPassword);
            
            if (encoder.matches(rawPassword, encodedPassword)) {
                logger.info(">>>> 비밀번호 매치");
                session.setAttribute("login", dbMember);
                return "member/loginSuccess";
            } else {
                logger.info(">>>> 비밀번호 매치하지 않음");
            }
        } else {
            logger.info(">>>> 회원 정보 없음");
        }

        // 로그인 실패 시 처리
        model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
        return "member/loginForm";
    }

    // 로그아웃 처리
	@RequestMapping("/logout")
    public String logout(HttpSession session) {
		logger.info("[MemberController - logoutMember] 요청");
		session.invalidate();
    	return "redirect:/member/loginForm";
    }
    
}
