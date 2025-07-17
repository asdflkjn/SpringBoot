package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.domain.MemberVO;

@Mapper 
public interface MemberDAO {
	
	@Select("SELECT * FROM Member ORDER BY id DESC")
    public List<MemberVO> getMemberList(); 

    @Select("SELECT * FROM member WHERE id=#{id}")
	public MemberVO getMember(MemberVO vo); 

    @Insert("INSERT INTO member(id, password, name) VALUES(#{id}, #{password}, #{name})")
    public Integer saveMember(MemberVO vo); 
    
//    public Integer updateMember(MemberVO vo);
//    
//    public Integer deleteMember(MemberVO vo);
}
