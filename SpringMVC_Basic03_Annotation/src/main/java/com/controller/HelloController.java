package com.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/*
 * implements Controller 작업해서 handleRequest 함수를 쓰는 방식은
 * 서비스요청 개수만큼 컨트롤러를 생성해야한다. (단점)
 * ex) 게시판 - 목록보기 >> listController
 * 			- 글쓰기  >> writeController
 *  		- 수정하기 >> editController
 * 
public class HelloController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

* 대안은 어노테이션이다! @Controller  -> method단위로 서비스를 매핑할 수 있다.
* ex) 게시판 - 목록보기 >> list()
* 		   - 글쓰기  >> write()
*  		   - 수정하기 >> edit()
* 
*/

@Controller
public class HelloController {
	public HelloController() {
		System.out.println("HelloController 생성자 호출");
	}
	
	@RequestMapping("/hello.do")  //<a href="hello.do"></a>
	public ModelAndView hello() {
		System.out.println("[hello.do method call]");
		ModelAndView mv = new ModelAndView();
		mv.addObject("greeting", getGeetring());
		mv.setViewName("Hello");
		
		return mv;
	}
	
	private String getGeetring() {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		String data = "";
		if(hour >= 6 && hour <=10) {
	         data="학습시간";
	      }else if(hour >= 11 && hour <=13) {
	         data="배고픈시간";
	      }else if(hour >= 14 && hour <=18) {
	         data="졸려운 시간";
	      }else {
	         data="go home";
	      }
		
		return data;
	}
	
}
