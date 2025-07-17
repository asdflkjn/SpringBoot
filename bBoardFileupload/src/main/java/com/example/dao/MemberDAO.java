package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.domain.MemberVO;

@Mapper 
public interface MemberDAO {
	// 전체 회원 목록 조회
//	@Select("SELECT * FROM member ORDER BY id DESC")
//    public List<MemberVO> getMemberList(); 
	
	@Select("SELECT m.id, m.password, m.name, mf.filename, mf.originFilename " +
	        "FROM member m LEFT JOIN member_file mf ON m.id = mf.member_id " +
	        "ORDER BY m.id DESC")
	List<MemberVO> getMemberList();


	// 단일 회원 조회 (String id로)
	@Select("SELECT * FROM member WHERE id=#{id}")
	public MemberVO getMember(String id);

	// 로그인용 회원 조회
	@Select("SELECT * FROM member WHERE id=#{id} AND password=#{password}")
	public MemberVO loginMember(MemberVO vo);
	
	// 상세 검색
//	@Select("select "
//			+ "	m.id id "
//			+ "	, m.name name "
//			+ "	, m.password password "
//			+ "	, mf.fileid fileid "
//			+ "	, mf.filename filename "
//			+ "	, mf.originFilename originFilename "
//			+ "	, mf.filepath filepath "
//			+ "from member m left outer join member_file mf "
//			+ "on m.id = mf.member_id "
//			+ "where m.id = #{id}")
//	public HashMap getMemberList(MemberVO vo); 
	
	// 회원 등록
    @Insert("INSERT INTO member(id, password, name) VALUES(#{id}, #{password}, #{name})")
    public Integer saveMember(MemberVO vo); 
    
    // 회원 정보 수정
	@Update("UPDATE member SET password=#{password}, name=#{name} WHERE id=#{id}")
    public Integer updateMember(MemberVO vo);
    
	// 회원 삭제
	@Delete("DELETE FROM member WHERE id=#{id}")
    public Integer deleteMember(MemberVO vo);

    // 최근 입력한 게시글 번호 검색
	@Select("SELECT id FROM member ORDER BY id DESC LIMIT 1")
    public String selectId();
}
