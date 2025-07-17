package com.example.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.MemberFileVO;
import com.example.domain.MemberVO;
import com.example.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MemberController {

    Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    // 전체 회원 목록 조회
    @RequestMapping("/getMemberList")
    public void getMemberList(Model m) {
        logger.info("[MemberController - getMemberList] 요청");
        List<MemberVO> memberList = memberService.getMemberList();
        m.addAttribute("memberList", memberList);
    }

    // 회원 등록 폼 이동 (step 방식)
    @RequestMapping("/{step}")
    public String movePage(@PathVariable String step) {
        logger.info("[MemberController] 요청: " + step);
        return "member/" + step;
    }

    // 회원 등록 처리
    @PostMapping("/saveMember")
    public String saveMember(MemberVO vo,
                             @RequestParam("file") MultipartFile file,
                             HttpSession session) {
        memberService.saveMember(vo, file);

        MemberFileVO memberFile = null;
        // 파일이 첨부되지 않은 경우 file.isEmpty()가 true
        if (file != null && !file.isEmpty()) {
            memberFile = memberService.saveMemberFile(vo, file);
            session.setAttribute("memberFile", memberFile);
        }

        session.setAttribute("member", vo);
        return "member/saveMember";
    }

    // 단일 회원 조회
    @RequestMapping("/getMember")
    public void getMember(MemberVO vo, Model m) {
        logger.info("[MemberController - getMember] 요청");
        HashMap result = memberService.getMember(vo);
        m.addAttribute("member", result);
    }

    // 회원 정보 수정 처리
    @RequestMapping("/updateMember")
    public String updateMember(MemberVO vo) {
        logger.info("[MemberController - updateMember] 요청");
        memberService.updateMember(vo);
        return "redirect:getMemberList";
    }

    // 회원 삭제 처리
    @RequestMapping("/deleteMember")
    public String deleteMember(MemberVO vo) {
        logger.info("[MemberController - deleteMember] 요청");
        memberService.deleteMember(vo);
        return "redirect:getMemberList";
    }
    

    
}
