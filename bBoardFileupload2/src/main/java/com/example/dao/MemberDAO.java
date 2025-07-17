package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.MemberVO;

@Mapper 
public interface MemberDAO {
	// 전체 회원 목록 조회
	List<MemberVO> getMemberList();

	// 단일 회원 조회
	public HashMap getMember(MemberVO vo);

	// 로그인용 회원 조회
//	public MemberVO loginMember(MemberVO vo);
	
	// 회원 등록
    public Integer saveMember(MemberVO vo); 
    
    // 회원 정보 수정
    public Integer updateMember(MemberVO vo);
    
	// 회원 삭제
    public Integer deleteMember(MemberVO vo);

}
