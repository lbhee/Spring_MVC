package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {
	/*

	WEB : Client 전송 데이터 (로그인 정보 , 회원정보) >> 서버 (데이터 받기) >> DB연동 ....

	1. 전통적인 방법 (request)
	public void searchInteral( HttpServletRequest request ) {
		String id = request.getParameter("id")
	}

	2. DTO 객체를 통한 전달 방법(게시판 데이터 , 회원가입 데이터)
	public void searchInteral(MemberDTO member ) {
		// input name="id"
		//선행조건 : insertmember.do?id=hong&name=김유신&age=100 
		//MemberDTO >> private id , private name , private age 가지고 있어야 한다
		//MemberDTO 자동 생성
		//setter 자동 주입
		//view 까지 자동으로 forward >> view 단에서 memberDTO key 값을 사용 
	}

	3. 편하게 사용하기
	public void serarchInternal(int page , int size){
		**넘어오는 parameter [이름]이 함수의 [parameter 명]과 같다면 데이터 받는다
		**internal.do?page=100&size=10
	}
	
	4. @RequestParam Annotation 사용하기 (3번 편하게 사용하기 단점 극복(유효성 체크할 수 있도록))
	   - 유효성 처리
	   - 기본값 설정

	5. REST API (method : GET, POST , PUT , DELETE )
	@PathVariable >> /member/{memberid} >> /member/100 또는 /member/1004
	  100또는 1004라는 값을 paramemter 사용할 수 있다

	*/
	
	@RequestMapping("/serch/internal.do")
	public ModelAndView searchInternal(@RequestParam(value = "query" , defaultValue = "bit") String query, 
									   @RequestParam(value = "p" , defaultValue = "10") int p) {
																	//디폴트값 설정
		System.out.println("param query : " + query);
		System.out.println("param p : " + p);
		
		return new ModelAndView("search/internal"); //public String으로한다면 -> return값이 view의 주소
	}
}
