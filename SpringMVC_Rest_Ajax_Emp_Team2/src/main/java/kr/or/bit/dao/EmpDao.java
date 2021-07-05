package kr.or.bit.dao;

import java.util.List;

import kr.or.bit.dto.EmpDto;

public interface EmpDao {
	
	//등록
	public int insert(EmpDto dto) throws Exception;
	
	//수정
	public int update(EmpDto dto) throws Exception;
	
	//삭제
	public int delete(int empno) throws Exception;
	
	//전체조회
	public List<EmpDto> emplist() throws Exception;
	
	//사번조회
	public EmpDto listByEmpno(int empno) throws Exception;
	
}
