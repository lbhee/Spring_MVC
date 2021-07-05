package com.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {
	
	@RequestMapping("/cookie/make.do")
	public String make(HttpServletResponse response) {
		response.addCookie(new Cookie("auth", "1004")); //jsp & servlect 동일
		return "cookie/CookieMake";
	}
	
	@RequestMapping("/cookie/view.do")
	public String view(@CookieValue(value="auth" , defaultValue = "1007") String auth) { //쿠키가 삭제되도 디폴트값으로 대체가능하다.
	//public String view(HttpServletRequest request)도 가능함.	
		
		System.out.println("클라이언트에서 read한 쿠키값 : " + auth);
		return "cookie/CookieView";
	}
}
