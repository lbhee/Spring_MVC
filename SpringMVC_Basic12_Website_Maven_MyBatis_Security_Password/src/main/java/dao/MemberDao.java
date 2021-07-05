package dao;

import java.sql.SQLException;

import vo.Member;

public interface MemberDao {
	
	//회원가입
	public int insertMember(Member member);
	
	//회원id검증(비동기처리)
	public int idCheck(String userid);
	
	//로그인
	public int loginCheck(String username, String password);
	
	//회원정보 얻기
	public Member getMember(String userid);
	
	//회원수정
	public int updateMember(Member member);
}
