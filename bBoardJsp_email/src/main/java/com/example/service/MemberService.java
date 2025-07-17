package com.example.service;

import java.util.List;

import com.example.domain.MemberVO;

public interface MemberService {
	
//    public List<MemberVO> getMemberList();
    
    public MemberVO getMember(MemberVO vo);
    
    public Integer saveMember(MemberVO vo);
    
//    public Integer updateMember(MemberVO vo);
    
//    public Integer deleteMember(MemberVO vo);
}
