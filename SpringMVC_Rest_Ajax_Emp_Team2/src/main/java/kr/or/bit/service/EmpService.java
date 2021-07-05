package kr.or.bit.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.EmpDto;




@Service
public class EmpService {

	@Autowired
	private SqlSession sqlsession;
	
	//등록
	public void insertEmp(EmpDto dto) throws Exception {
		EmpDao dao = sqlsession.getMapper(EmpDao.class);
		int result = dao.insert(dto);
		
		if(result > 0){
			System.out.println("등록 성공");
		}else{
			System.out.println("등록 실패");
		}
	}
	
	//수정
	public void updateEmp(EmpDto dto) throws Exception{
		EmpDao dao = sqlsession.getMapper(EmpDao.class);
		int result = dao.update(dto);
		if(result > 0){
			System.out.println("업데이트 성공");
		}else{
			System.out.println("업데이트 실패");
		}
	}

	//삭제
	public void deleteEmp(int empno) throws Exception {
		EmpDao dao = sqlsession.getMapper(EmpDao.class);
		int result = dao.delete(empno);
		if(result > 0){
			System.out.println("삭제 성공");
		}else{
			System.out.println("삭제 실패");
		}
	}
	
	//전체조회
	public List<EmpDto> listEmp() throws Exception {
		List<EmpDto> list = null;
		
		EmpDao dao = sqlsession.getMapper(EmpDao.class);
		list = dao.emplist();
		
		return list;
	}
	
	//사번조회
	public EmpDto listByEmpno(int empno) throws Exception {
		EmpDao dao = sqlsession.getMapper(EmpDao.class);
		EmpDto emp = dao.listByEmpno(empno);
		
		return emp;
	}
}
