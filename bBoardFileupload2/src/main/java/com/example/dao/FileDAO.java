package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.FileVO;

@Mapper
public interface FileDAO { //매퍼랑 짝꿍 만들기 위해서 요 DAO가 필요

	public Integer insertFile(FileVO fvo); //받을거면 int, 받을 생각 없으면 void, 안줘도 public인 이유는 interface여서.
	
	public FileVO selectFile(FileVO fvo); // 무조건 하나 받는다. pk 검색이라서, 여러 개 받을 거면 List<FileVO>로 적으면 된다.
	
	
}
