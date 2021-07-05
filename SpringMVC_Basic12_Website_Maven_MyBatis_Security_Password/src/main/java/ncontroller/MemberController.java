package ncontroller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.MemberService;
import vo.Member;



@Controller
@RequestMapping("/joinus/")
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//수정하기 화면
	@RequestMapping(value="memberconfirm.do",method=RequestMethod.GET)
	public String memberConfirm(){
		return "joinus/memberConfirm";
	}
	

	//수정하기 처리
	@RequestMapping(value="memberconfirm.do",method=RequestMethod.POST)
	public String memberConfirm(@RequestParam("password") String rawPassword,	Principal principal){
		String viewpage="";     //화면에 입력한 비밀번호
		
		//회원정보
		Member member = service.getMember(principal.getName());
		
		
		//DB에서 가져온 암호화된 문자열
		String encodedPassword = member.getPwd();
		
		System.out.println("rawPassword : "+rawPassword );
		System.out.println("encodepassword : " + encodedPassword);
		
		boolean result = bCryptPasswordEncoder.matches(rawPassword, encodedPassword); //비밀번호 일치확인
		
		if(result){
			viewpage="redirect:memberupdate.do";
		}else{
			viewpage="redirect:memberconfirm.do";
		}
		
		return viewpage;
	}
	
	//수정하기 업데이트 화면
	@RequestMapping(value="memberupdate.do", method=RequestMethod.GET)
	public String memberUpdate(Model model, Principal principal){
		Member member = service.getMember(principal.getName());
		model.addAttribute("member", member);
		return "joinus/memberUpdate";
	}
	
	//수정하기 업데이트 처리
	@RequestMapping(value="memberupdate.do", method=RequestMethod.POST)
	public String memberUpdate(Model model, Member member, Principal principal){
		
		Member updatemember = service.getMember(principal.getName());
		
		updatemember.setName(member.getName());
		updatemember.setCphone(member.getCphone());
		updatemember.setEmail(member.getEmail());
		updatemember.setPwd(bCryptPasswordEncoder.encode(member.getPwd())); //비밀번호 다시 암호화
		service.updateMember(updatemember);
		return "redirect:/index.do";
	}
}
