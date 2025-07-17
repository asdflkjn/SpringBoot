package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MemberDAO;
import com.example.domain.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDAO MemberDAO;
    
    @Override
    public List<MemberVO> getMemberList() {
        return MemberDAO.getMemberList();
    }
    
    public MemberVO getMember(MemberVO vo) {
    	return MemberDAO.getMember(vo);
    }
    
    public Integer saveMember(MemberVO vo) {
    	return MemberDAO.saveMember(vo);
    }
    
//    public Integer updateMember(MemberVO vo) {
//    	return MemberDAO.updateMember(vo);
//    }
//    
//    public Integer deleteMember(MemberVO vo) {
//    	return MemberDAO.deleteMember(vo);
//    }
    
    
}
