package com.example.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.BoardDAO;
import com.example.dao.FileDAO;
import com.example.domain.BoardVO;
import com.example.domain.FileVO;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDAO boardDAO;
    
    @Autowired
    private FileDAO fileDAO;
    
    
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public List<BoardVO> getBoardList() {
        return boardDAO.getBoardList();
    }
    
    public HashMap getBoard(BoardVO vo) {
    	//반드시 확인
    	logger.info("<<<<< 상세보기내용 pk값 >>>>>" + vo);
    	HashMap map = boardDAO.getBoard(vo);
    	logger.info("<<<<< 상세보기내용 출력 >>>>>" + map);
    	
    	return map;
    }
    
    @Transactional
    public void saveBoard(BoardVO vo, FileVO fvo) {
    	// 파일첨부가 없는 게시글 입력
    	boardDAO.saveBoard(vo);
    	
    	// 파일첨부가 있는 게시글 입력
    	if(fvo != null) {
    		fvo.setBoard_seq(boardDAO.selectId());
    		fileDAO.insertFile(fvo);
    	}
    }
    
    public Integer updateBoard(BoardVO vo) {
    	return boardDAO.updateBoard(vo);
    }
    
    public Integer deleteBoard(BoardVO vo) {
    	return boardDAO.deleteBoard(vo);
    }
    
    
}
