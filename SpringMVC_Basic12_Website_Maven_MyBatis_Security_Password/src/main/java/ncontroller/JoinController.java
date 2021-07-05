package ncontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.MemberDao;
import service.JoinService;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController {
	
	@Autowired
	private JoinService service;
	
	////////////////////////////////////////////////////////
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	/////////////////////////////////////////////////////// 
	
	//회원가입페이지(GET)
	@RequestMapping(value = "join.do" , method = RequestMethod.GET)
	public String join() {
		return "joinus/join";
	}
	
	//회원가입처리(POST)
	@RequestMapping(value = "join.do" , method = RequestMethod.POST)
	public String join(Member member) {
		int result = 0;
		String viewpage = "";
		
		//회원가입할때 입력한 비밀번호를 암호화시킨다.////////////////////////////////////
		member.setPwd(this.bCryptPasswordEncoder.encode(member.getPwd()));
		////////////////////////////////////////////////////////////////////
		
		result = service.insertMember(member);
		if (result > 0) {
			System.out.println("가입 성공");
			viewpage = "redirect:/index.do";
		} else {
			System.out.println("가입 실패");
			viewpage = "join.do";
		}
		return viewpage;
	}
	
	
	//로그인요청
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
		return "joinus/login";
	}
	
	//로그인 처리는 Spring이 자동화 처리!!
	
	
	
}
