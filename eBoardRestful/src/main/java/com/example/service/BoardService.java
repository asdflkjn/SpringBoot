package com.example.service;

import java.util.List;

import com.example.domain.BoardVO;

public interface BoardService {
	
    // 전체 게시판 목록 조회
    public List<BoardVO> getBoardList();
    
    public BoardVO getBoard(BoardVO vo);
    
    public Integer saveBoard(BoardVO vo);
    
    public Integer updateBoard(BoardVO vo);
    
    public Integer deleteBoard(BoardVO vo);
}
