package kr.or.bit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.bit.dto.Emp;

@RestController
@RequestMapping("/empdata")
public class EmpController {

	//private static final logger 사용옵션
	
	//@Autowired
	//Service
	//dao
	/*
		GET    ( /board )                    >> 전체 게시물 조회
		GET    ( /board/100 )                >> 100번 게시물 조회
		POST   ( /board  + 데이터(json))       >> 게시물 등록
		DELETE ( /board/100)                 >> 100게시물 삭제
		PUT    ( /board/100 + 데이터(json))    >> 100번 게시물 수정
	*/
	
	
	//POST   ( /board  + 데이터(json))       >> 게시물 등록(insert)
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody Emp emp) {
		try {
			//등록
			System.out.println("등록성공");
			return new ResponseEntity<String>("emp data register success", HttpStatus.OK); //200번대
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); //400번대
		}
	}
	
	
	//PUT    ( /board/100 + 데이터     >> 100번 게시물 수정(update)
	@RequestMapping(value = "/{no}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable("no") int no, @RequestBody Emp emp) {
		try {
			//등록
			System.out.println("수정요청 사번 : " + no);
			System.out.println(emp.toString());
			System.out.println("변경성공");
			return new ResponseEntity<String>("emp data update success", HttpStatus.OK); //200번대
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); //400번대
		}
	}
	
	
	//select empno, ename from emp where empno=100  >>조건 조회
	//http://localhost:8090/bit/empdata/100/1/2
	@RequestMapping(value="/{no}/{page}/{index}", method = RequestMethod.GET)
	public Map<String , Object> getEmp(@PathVariable("no") int no, @PathVariable("page") int page, @PathVariable("index") String index) {
		
		return new HashMap<String, Object>();
	}
}
