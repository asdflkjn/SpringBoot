package com.example.controller;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.MemberFileVO;
import com.example.domain.MemberVO;
import com.example.service.MemberService;
import com.example.util.MD5Generator;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService service;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  
//    @RequestMapping("/getMember")
//    public String getMember(@RequestParam("id") String id, Model model) {
//    	MemberVO member = service.getMember(id);
//        model.addAttribute("Member", member);
//        return "member/getMember";
//    }

    @RequestMapping("/getMember")
    public String getMember(@RequestParam String id, Model model) {
        model.addAttribute("Member", service.getMember(id));
        return "member/getMember";
    }
    
	@RequestMapping("/getMemberList") 
	public void getMemberList(Model m) {
		logger.info("[MemberController - getMemberList] 요청");
	    List<MemberVO> listMember = service.getMemberList();

	    m.addAttribute("memberList", listMember);
	}
    
    // 동적 페이지 매핑
    @RequestMapping("/{step}")
    public String stepPage(@PathVariable String step) {
        logger.info("[MemberController] 동적 페이지 요청: " + step);
        return "member/" + step;
    }

    // 회원가입 처리
//    @RequestMapping("/saveMember")
//    public String saveMember(MemberVO vo) {
//        logger.info("[MemberController - saveMember] 요청");
//
//		// [예시] password의 암호화
//		String rawPassword = vo.getPassword();
//		logger.info("[암호화 하기 전] 패스워드" + rawPassword);
//		
//		String securePassword = encoder.encode(rawPassword);
//		logger.info("[암호화 하기 후] 패스워드" + securePassword);
//		
//		//암호화된 비밀번호로 디비에 입력하기
//		vo.setPassword(securePassword);
//		service.saveMember(vo);
//		
//		return "member/saveMember"; 
//	}
    
    
	// Fileupload 볼 때 , 2nd
	@RequestMapping("/saveMember")
	public String saveMember(MemberVO vo, @RequestParam("file") MultipartFile files) { // 앞에서 name이 file인 애를 이 클래스의 files로 받을거야
		logger.info("[MemberController - saveMember] 요청"); 	
		
	    // 1. 암호화는 파일 첨부 여부와 무관하게 항상 먼저 실행
	    String rawPassword = vo.getPassword();
	    String securePassword = encoder.encode(rawPassword);
	    vo.setPassword(securePassword);
	    
		try {
			// 파일의 원래 이름
			String originFilename = files.getOriginalFilename(); // 변수를 하나 준다.
//			logger.info("[[원래파일명]]" + originFilename);
			
			// 파일을 첨부한 경우
			if(originFilename != null && !originFilename.equals(""))
			{
				// 변경된 파일이름
				String filename = new MD5Generator(originFilename).toString();
//				logger.info("[[변경된 파일명]]" + filename);
				// #############[추후작업] 확장자 붙이기
				String ext = originFilename.substring(originFilename.lastIndexOf("."));
//				String filename = new MD5Generator(originFilename).toString() + ext;
				
				// 정해진 폴더를 지정 (절대경로 쓰면 안됨)
				String savepath = System.getProperty("user.dir");
				savepath+="\\src\\main\\resources\\static\\files";
//				logger.info("[[저장경로]]" + savepath);
				
				if(!new File(savepath).exists()) {
					new File(savepath).mkdir(); 
				}
				
				// 실제파일을 저장하고 디비에 파일정보를 입력하기
				String filepath = savepath + "\\" + filename;
				files.transferTo(new File(filepath)); //실제로 저장하는 애 , 실제로 업로딩되는 코딩 한줄
				logger.info("[[실제로 저장경로]]" + filepath);
				
				// 디비 저장
				MemberFileVO fileVO = new MemberFileVO();
				fileVO.setFilename(filename);
				fileVO.setOriginFilename(originFilename);
				fileVO.setFilepath(filepath); // 추후에 savepath로 넣어주는 것 권장
				
				service.saveMember(vo, fileVO); // 게시판에 들어가는 vo가 들어가서 입력하고, fileVO가 들어가서 실제적으로 입력
				
			}else {
				service.saveMember(vo, null);
			}
						
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	return "redirect:getMemberList";		
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

        MemberVO dbMember = service.loginMember(vo);

        if (dbMember != null) {
            String rawPassword = vo.getPassword(); // 사용자가 입력한 비밀번호
            String encodedPassword = (String) dbMember.getPassword(); // DB에 저장된 암호화된 비밀번호

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
