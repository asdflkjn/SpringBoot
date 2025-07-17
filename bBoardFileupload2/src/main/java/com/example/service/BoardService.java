package com.example.service;

import java.util.HashMap;
import java.util.List;

import com.example.domain.BoardVO;
import com.example.domain.FileVO;

public interface BoardService {
	
    // 전체 게시판 목록 조회
    public List<BoardVO> getBoardList();
    
    public HashMap getBoard(BoardVO vo);
    
    public void saveBoard(BoardVO vo, FileVO fvo);
    
    public Integer updateBoard(BoardVO vo);
    
    public Integer deleteBoard(BoardVO vo);
}
