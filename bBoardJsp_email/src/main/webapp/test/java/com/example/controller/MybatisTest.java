package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.BoardVO;

@SpringBootTest //매퍼 확인
public class MybatisTest {

	Logger log = LoggerFactory.getLogger(MybatisTest.class);
	
	@Autowired
	private DataSource dataSource; 								//DataSource는 데이터베이스랑 연결한 애. 일반은 null값이라 자동 주입
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;				//마찬가지. 니가 널값 잡은거 나한테 주입해줘
	
	@Test
	public void selectBoardTest() {
		try {
			SqlSession sess = sqlSessionFactory.openSession();
			List<BoardVO> list = sess.selectList("com.example.dao.BoardDAO.getBoardList");
			for(BoardVO vo: list) {
				log.info("[BoardVO 결과]" + vo.toString());
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void insertBoardTest() {
		try {
			SqlSession sess = sqlSessionFactory.openSession();
			BoardVO vo = new BoardVO();
			vo.setTitle("제목");
			vo.setWriter("홍길동");
			vo.setContent("오늘도 즐");
			
			int result = sess.insert("com.example.dao.BoardDAO.saveBoard", vo); // insert, delete, update에는 데이터베이스는 항상 몇 개의 행을 수행했다고 int값을 리턴해줘. 그걸 받아.
			assertEquals(1, result);
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	
}
// 컨트롤러, 서비스, 다오 안 탈거고, 매퍼 sql 정상적으로 작성했는지만 확인할거야