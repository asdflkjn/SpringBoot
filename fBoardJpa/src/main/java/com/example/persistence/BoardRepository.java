package com.example.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.BoardVO;

//  CrudRepository<엔티티클래스, PK자료형> 상속받기
public interface BoardRepository extends CrudRepository<BoardVO, Integer> { // BoardVO라는 엔티티와 연결할거고, 걔의 키값이 integer야
	//sql 하나도 몰라도 돼 비워둬
}
