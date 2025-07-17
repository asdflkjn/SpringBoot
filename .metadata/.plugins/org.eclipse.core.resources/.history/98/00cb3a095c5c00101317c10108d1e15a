package com.example.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.MemberDAO;
import com.example.dao.MemberFileDAO;
import com.example.domain.MemberFileVO;
import com.example.domain.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private MemberFileDAO memberFileDAO;

    // 전체 회원 목록 조회
    @Override
    public List<MemberVO> getMemberList() {
        return memberDAO.getMemberList();
    }

    // 단일 회원 조회
    @Override
    public HashMap getMember(MemberVO vo) {
        return memberDAO.getMember(vo);
    }

    // 회원 등록 + 파일 업로드
    @Override
    @Transactional
    public Integer saveMember(MemberVO vo, MultipartFile file) {
        // 1. 회원 정보 저장
        Integer result = memberDAO.saveMember(vo);

        // 2. 파일이 있으면 파일 저장 및 DB 저장
        if (file != null && !file.isEmpty()) {
            String savePath = System.getProperty("user.dir") + "/src/main/resources/static/files";
            File dir = new File(savePath);
            if (!dir.exists()) dir.mkdirs();

            String originFilename = file.getOriginalFilename();
            String filename = UUID.randomUUID().toString() + originFilename.substring(originFilename.lastIndexOf("."));
            File saveFile = new File(savePath, filename);

            try {
                file.transferTo(saveFile);
            } catch (Exception e) {
                e.printStackTrace();
                // 예외 처리
            }

            MemberFileVO memberFileVO = new MemberFileVO();
            memberFileVO.setMember_id(vo.getId());
            memberFileVO.setOriginFilename(originFilename);
            memberFileVO.setFilename(filename);
            memberFileVO.setFilepath("/files/" + filename);

            memberFileDAO.saveMemberFile(memberFileVO); // 실제 DB 저장
        }

        return result;
    }

    // 회원 정보 수정
    @Override
    public Integer updateMember(MemberVO vo) {
        return memberDAO.updateMember(vo);
    }

    // 회원 삭제
    @Override
    public Integer deleteMember(MemberVO vo) {
        return memberDAO.deleteMember(vo);
    }
}
