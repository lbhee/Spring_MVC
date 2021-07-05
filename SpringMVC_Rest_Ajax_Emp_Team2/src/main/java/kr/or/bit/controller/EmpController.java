package kr.or.bit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.bit.dto.EmpDto;
import kr.or.bit.service.EmpService;

@RestController
@RequestMapping("/emp")
public class EmpController {

	@Autowired
	private EmpService service;
	
	//등록
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody EmpDto emp) {
		try {
			service.insertEmp(emp);
			return new ResponseEntity<String>("emp data insert success", HttpStatus.OK); //200번대
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); //400번대
		}
	}
	
	//수정
	@RequestMapping(value = "/{empno}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable("empno") int empno, @RequestBody EmpDto emp) {
		try {
			//등록
			emp.setEmpno(empno);
			service.updateEmp(emp);
			return new ResponseEntity<String>("emp data update success", HttpStatus.OK); //200번대
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); //400번대
		}
	}
	
	
	//삭제
	@RequestMapping(value = "/{empno}" , method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("empno") int empno) {
		try {
			//등록
			service.deleteEmp(empno);
			return new ResponseEntity<String>("emp data delete success", HttpStatus.OK); //200번대
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); //400번대
		}
	}
	
	//전체조회
	@RequestMapping(value="", method = RequestMethod.GET)
	public List<EmpDto> list() {
		List<EmpDto> list = new ArrayList<EmpDto>();
		try {
			list = service.listEmp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//사번조회
	@RequestMapping(value="/{empno}", method = RequestMethod.GET)
	public EmpDto getEmp(@PathVariable("empno") int empno) {
		EmpDto emp = new EmpDto();
		try {
			emp = service.listByEmpno(empno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emp;
	}
}
