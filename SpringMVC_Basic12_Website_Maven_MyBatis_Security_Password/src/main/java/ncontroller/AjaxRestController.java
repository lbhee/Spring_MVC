package ncontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.JoinService;

@RestController // @Controller + @ResponseBody (비동기함수만 있을때만!!)
public class AjaxRestController {

	@Autowired
	private JoinService joinservice;

	// 아이디 중복체크
	
	@RequestMapping(value = "/joinus/idcheck.do", method = RequestMethod.POST)
	public Map<String, Integer> idCheck(@RequestParam("userid") String userid) {

		Map<String, Integer> mapResult = new HashMap<String, Integer>();

		int result = joinservice.idCheck(userid);

		if (result > 0) {
			System.out.println("아이디 중복");
			mapResult.put("result", result);
		} else {
			System.out.println("아이디 사용가능");
			mapResult.put("result", result);
		}

		System.out.println(mapResult);

		return mapResult;
	}
	
	/*
	@RequestMapping(value = "/joinus/idcheck.do", method = RequestMethod.POST)
	public String idCheck(@RequestParam("userid") String userid) {

		String resultString = "";

		int result = joinservice.idCheck(userid);

		if (result > 0) {
			resultString = "이미 사용중인 아이디입니다.";
		} else {
			resultString = "사용가능한 아이디입니다.";
		}

		return resultString;
	}
	*/

}

//요즘은 @RestController를 많이쓴다.
//과거엔 @Responsebody와 jasonview를 자주썼음.