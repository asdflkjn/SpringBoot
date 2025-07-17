package com.example.service;

import java.util.HashMap;
import java.util.List;

import com.example.domain.MemberFileVO;
import com.example.domain.MemberVO;

public interface MemberService {
	
	// 전체 검색
    public List<MemberVO> getMemberList();
    
    // 상세 검색
    public MemberVO getMember(String id);
    
    // 로그인
 	public MemberVO loginMember(MemberVO vo);
 	
 	// 입력
    public void saveMember(MemberVO vo, MemberFileVO fvo);
    
    // 수정
    public void updateMember(MemberVO vo, MemberFileVO fvo);
    
    // 삭제
    public Integer deleteMember(MemberVO vo);
}
