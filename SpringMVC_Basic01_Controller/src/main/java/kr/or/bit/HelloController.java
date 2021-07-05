package kr.or.bit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller{ //서블릿역할
	
	public HelloController() {
		System.out.println("HelloController 객체생성");
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception { //doGet, doPost 역할
		System.out.println("HelloController 요청실행 : handleRequest 함수실행" );
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("name", "bituser"); //request.setAtrribute("name", "bituser");
		mav.setViewName("Hello");
		//InternalResourceViewResolver에 의해서 view단의 주소가 조합된다.
		//  /WEB-INF/views/ + Hello + .jsp   >> /WEV-INF/views/Hello.jsp
		//원래방법 -> mav.setViewName("/WEV-INF/views/Hello.jsp")
		
		return mav;
	}

}
