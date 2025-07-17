package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BoardDAO;
import com.example.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDAO boardDAO;
    
    @Override
    public List<BoardVO> getBoardList() {
        return boardDAO.getBoardList();
    }
    
    public BoardVO getBoard(BoardVO vo) {
    	return boardDAO.getBoard(vo);
    }
    
    public Integer saveBoard(BoardVO vo) {
    	return boardDAO.saveBoard(vo);
    }
    
    public Integer updateBoard(BoardVO vo) {
    	return boardDAO.updateBoard(vo);
    }
    
    public Integer deleteBoard(BoardVO vo) {
    	return boardDAO.deleteBoard(vo);
    }
    
    
}
