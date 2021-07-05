package kosta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import dto.EmpDto;
import service.EmpService;

@RestController   //@Controller + @ResponseBody  (비동기함수만 있을때만!!)
public class AjaxRestController {

	@Autowired
	private EmpService empservice;
	
	@RequestMapping(value="restcon.ajax")
	public List<EmpDto> ajaxResponseBody(){
	
		List<EmpDto> list = empservice.getEmpList();
		return list;  
	}
	
	
	@RequestMapping(value="view.ajax")
	public String ViewPage(){  //converter에 의해서 문자열 전달(비동기형태로)
		System.out.println("view.ajax");
		//ModelAndView mv = new ModelAndView("view.jsp"); 
		return "view.jsp 문자열 리턴";
	}
	
	
	
}

//요즘은 @RestController를 많이쓴다.
//과거엔 @Responsebody와 jasonview를 자주썼음.