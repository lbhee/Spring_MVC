package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.NewArticleCommand;
import com.service.ArticleService;

/*
	- 클라이언트가 요청을 보낸다
	  1. 화면보여주세요(글쓰기, 로그인 등) -> write.do , login.do
	  2. 처리해주세요(글쓰기 입력처리, 로그인 완료처리 등) -> writeOk.do , loginOk.do
	  
	  요청주소에 따라서 화면/처리 달라진다.
	  
	- 클라이언트가 요청주소 1개를 가지고 나누어서 쓸 수 없을까?
	  (요청주소 하나로 화면도 보여주고, 처리도할 수 있도록)
	  근거 : 글쓰기화면요청은GET, 글쓰기입력처리POST
	  >>http://localhost:8090/SpringMVC/article/newArticle.do
	  전송방식이 GET -> 화면 view제공
	   		 POST -> 서비스 intert, update제공
	  
*/


@Controller
@RequestMapping("/article/newArticle.do")
public class NewArticleController {
	
	private ArticleService articleservice;
	
	@Autowired
	public void setArticleservice(ArticleService articleservice) {
		this.articleservice = articleservice;
	}

	@RequestMapping(method = RequestMethod.GET) //화면제공
	public String form() { //함수의 리턴타입이 String이면 view의 주소라고 하자!
		return "article/newArticleForm";
		//  /WEB-INF/views/ + article/newArticleForm + .jsp
	}
	
	//1. 데이터를 받는 가장 전통적인 방법 >> submit(HttpServletRequest request) >> 코드량이 많아서 Spring은 다른방법을 쓴다.
	/*
	@RequestMapping(method = RequestMethod.POST) //insert처리
	public ModelAndView submit(HttpServletRequest request) {
		
		NewArticleCommand article = new NewArticleCommand();
		article.setParentId(Integer.parseInt(request.getParameter("parentId")));
		article.setTitle(request.getParameter("title"));
		article.setContent(request.getParameter("content"));
		
		//NewArticleController클래스가 service클래스가 필요하다.
		this.articleservice.writeAritcle(article);
		//처리완료
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("newArticleCommand", article);
		mv.setViewName("article/newArticleSubmitted");
		
		return mv;
	}
	*/
		
	
	//2. Spring에서 parameterfmf DTO 객체로 받기
	/*
	//  - 자동화 전제조건 : input태그의 name속성값이 DTO객체의 memberfield명과 동일해야한다.
	@RequestMapping(method = RequestMethod.POST) //insert처리
	public ModelAndView submit(NewArticleCommand command) { //파라메터로 DTO타입명시하면
		
		//1. 자동 DTO객체 생성 : NewArticleCommand article = new NewArticleCommand();
		//2. 넘어온 파라메터 값을 setter를 통해서 자동주입
		//3. NewArticleCommand 객체가 IOC컨테이너 안에 자동생성 >> id값이 자동 >> id="newArticleCommand"
		
		//NewArticleController클래스가 service클래스가 필요하다.
		this.articleservice.writeAritcle(command);
		//처리완료
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("newArticleCommand", command);
		mv.setViewName("article/newArticleSubmitted");
		
		return mv;
	}
	*/
	
	
	@RequestMapping(method = RequestMethod.POST) //insert처리
	public String submit(@ModelAttribute("Articledata") NewArticleCommand command) { //파라메터로 DTO타입명시하면
		//자동화 전제조건 : input태그의 name속성값이 DTO객체의 memberfield명과 동일해야한다.
		//1. 자동 DTO객체 생성 : NewArticleCommand article = new NewArticleCommand();
		//2. 넘어온 파라메터 값을 setter를 통해서 자동주입
		//3. NewArticleCommand 객체가 IOC컨테이너 안에 자동생성 >> id값이 자동 >> id="newArticleCommand"
		
		//NewArticleController클래스가 service클래스가 필요하다.
		this.articleservice.writeAritcle(command);
		//처리완료
		
		//뷰페이지가 데이터를 어떻게 받느냐? 자동화
		//id값을 key로 자동 forward >> key값 : newArticleCommand 이름으로 forward
		
		//<다른방법> forward되는 이름을 내가정의하고 싶으면
		//mv.addObject("내마음대로", command);  --> @ModelAttribute("Articledata") 해주면
		//mv.addObject("Articledata", command);
		return "article/newArticleSubmitted"; //뷰의 주소
		
	}
	
}
