package com.example.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.domain.MemberFileVO;
import com.example.domain.MemberVO;

public interface MemberService {
	
	// 전체 검색
	List<MemberVO> getMemberList();
    
	// 단일 회원 조회
	HashMap getMember(MemberVO vo);
		
	// 회원 등록
    Integer saveMember(MemberVO vo, MultipartFile file); 
    
    // 회원 정보 수정
    Integer updateMember(MemberVO vo);
    
	// 회원 삭제
    Integer deleteMember(MemberVO vo);

    MemberFileVO saveMemberFile(MemberVO vo, MultipartFile file);
}
