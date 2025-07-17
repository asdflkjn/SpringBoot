package com.example.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.MemberDAO;
import com.example.dao.MemberFileDAO;
import com.example.domain.MemberFileVO;
import com.example.domain.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberdao;
	
	@Autowired
	private MemberFileDAO memberfiledao;
    
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public List<MemberVO> getMemberList() {
        return memberdao.getMemberList();
    }
    
    @Override
    public MemberVO getMember(String id) {
        return memberdao.getMember(id);
    }
    
    @Transactional
    @Override
    public void updateMember(MemberVO vo, MemberFileVO fvo) {
    	// 파일첨부가 없는 게시글 입력
    	memberdao.updateMember(vo);
    	
    	// 파일첨부가 있는 게시글 입력
    	if(fvo != null) {
    		fvo.setMember_id(memberdao.selectId());
    		memberfiledao.insertFile(fvo);
    	}
    }
    
    @Override
    public Integer deleteMember(MemberVO vo) {
    	return memberdao.deleteMember(vo);
    }
    
	@Override
	public MemberVO loginMember(MemberVO vo) {
		return memberdao.loginMember(vo);
	}
	
	@Transactional
	@Override
	public void saveMember(MemberVO vo, MemberFileVO fvo) {
		// 파일첨부가 없는 게시글 입력
		memberdao.saveMember(vo);
		
		//파일첨부가 있는 경우
		if(fvo != null) {
			fvo.setMember_id(memberdao.selectId());
			memberfiledao.insertFile(fvo);
		}
	}
    
}
