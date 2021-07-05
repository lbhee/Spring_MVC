package ncontroller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.NoticeDao;
import vo.Notice;

@Controller
@RequestMapping("/customer/")
public class CustomerController {
	
	private NoticeDao noticedao;
	
	@Autowired
	public void setNoticedao(NoticeDao noticedao) {
		this.noticedao = noticedao;
	}
	
	/*
		1. method안에서 return type [String] 리턴값이 뷰의 주소
		2. public ModelAndView notices .. > ModelAndView 객체생성 > 데이터, 뷰 설정 > return
		3. public String notices (Model model) { 함수가 실행시 내부적으로 Model객체의 주소가 들어온다}
		
	*/
	
	//공지사항 보기
	//public List<Notice> getNotices(int page, String field, String query)
	@RequestMapping("notice.htm") // /customer/notice.htm
	public String notices(String p, String f, String q, Model model){ //파라미터는 스트링값으로 들어옴
		 //리턴을 뷰주소로 해주기때문에 리턴타입 스트링	
		
		  //default 값 설정
		  int page = 1;
		  String field="TITLE";
		  String query = "%%";
		  
		  if(p != null && !p.equals("")) {
		     page = Integer.parseInt(p);
		  }
		  
		  if(f != null && !f.equals("")) {
		     field = f;
		  }
		  
		  if(q != null && !q.equals("")) {
		     query = q;
		  }
		  
		  //DAO 작업
		  List<Notice> list = null;
		  try {
		  	  list = noticedao.getNotices(page, field, query); //공지사항 리스트반환
		  } catch (ClassNotFoundException e) {
		  	  e.printStackTrace();
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
		  
		  //Spring 적용
		  /*
		   * 리턴값이 ModelAndView 타입일때
		  ModelAndView mv = new ModelAndView();
		  mv.addObject("list", list);
		  mv.setViewName("notice.jsp");
		  return mv;
		  */
		  
		  model.addAttribute("list", list); //뷰단에서 사용하기위해 담아주기
		  //return "notice.jsp";
		  return "customer/notice";  // /WEB-INF/views/ + customer/notice + .jsp
	}
	
	//공지사항 상세보기
	@RequestMapping("noticeDetail.htm")
	public String noticeDetail(String seq, Model model){
		
		Notice notice = null;
		try {
			notice = noticedao.getNotice(seq); //상세내용들어있는 notice객체 반환
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*
		ModelAndView  mv = new ModelAndView();
		mv.addObject("notice", notice);
		mv.setViewName("noticeDetail.jsp");
		*/
		
		model.addAttribute("notice", notice); 
		//return "noticeDetail.jsp";  //여기에서 ${notice.title} 이런식으로 사용된다.
		return "customer/noticeDetail";
	}
	
	//5.x.x버전에서는
	//@GetMapping   --> method = RequestMethod.GET  열거형대체
	//@PostMapping  --> method = RequestMethod.POST
	
	//공지사항 글쓰기 화면으로(GET)
	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.GET)
	public String noticeReg() {
		//return "noticeReg.jsp";
		return "customer/noticeReg";
	}
	
	//공지사항 글쓰기 처리(POST)
	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.POST)
	public String noticeReg(Notice n, HttpServletRequest request) {
		//System.out.println(n.toString());
		
		//Notice DTO변경사항
		//private List<CommonsMultipartFile> files
		
		//files[0] --> 1.jpg
		//files[1] --> 2.jpg
		
		List<CommonsMultipartFile> files = n.getFiles();
		List<String> filenames = new ArrayList<String>(); //파일명관리
		
		if(files != null && files.size() > 0) { //1개라도 업로드된 파일이 존재한다면
			for(CommonsMultipartFile multifile : files) {
				String filename = multifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로(첨부파일 업로드경로) 
				String fpath = path + "\\" + filename;
				
				System.out.println(fpath);
				
				if(!filename.equals("")) { //실파일 업로드(웹서버에)
					
					FileOutputStream fs = null; //데이터를 바이트 단위로 출력하여 파일로 만드는 스트림
					try {
						     fs = new FileOutputStream(fpath); //스트림에 첨부파일을 담는다.
						     fs.write(multifile.getBytes()); //스트림을 write해준다.
						     
						     filenames.add(filename); //db에 입력될 파일명
						     
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						 try {
							fs.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

		//파일명추출(DTO구성)
		//파일업로드를 2개한다는 전제
		n.setFileSrc(filenames.get(0)); //dto에 파일이름 넣어주기(다른건 파라미터에 Notice n하면서 자동으로 들어감)
		n.setFileSrc2(filenames.get(1));
		
		//DB에넣기
		try {
			noticedao.insert(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//insert나 update를 하고나면 .. (f5누르면 계속글이써진다 -> 이동시켜야함*리스트로이동*) 
		//location.href   or   redirect
		//서버에게 새로운요청.. 목록보기
		//"redirect:notice.htm"   = location.href  , response.sendRedirect(서블릿/jsp)
		return "redirect:notice.htm";
		//(DB, Session에 변화가 생기는 요청은 redirect로 , 단순 list조회 및 검색은 forward방식으로)
	}
	
	//공지사항 글수정하기 화면으로(데이터가 뿌려진 화면 - GET)
	@RequestMapping(value = "noticeEdit.htm", method = RequestMethod.GET)
	public String noticeEdit(String seq, Model model) {
		
		Notice notice = null;
		
		try {
			notice = noticedao.getNotice(seq); //게시물상세읽어오기
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("notice", notice); //담기(noticeEdit.jsp에서 사용하기위해)
		
		//return "noticeEdit.jsp";
		return "customer/noticeEdit";
	}
	
	//공지사항 글수정 처리(POST)
	@RequestMapping(value = "noticeEdit.htm", method = RequestMethod.POST)
	public String noticeEdit(Notice n, HttpServletRequest request) {
		
		List<CommonsMultipartFile> files = n.getFiles();
		List<String> filenames = new ArrayList<String>();  //파일명 관리
		
		if(files != null  && files.size() > 0) {  //1개라 업로드된 파일이 존재하면
				for(CommonsMultipartFile  mutifile  : files) {
					String filename =  mutifile.getOriginalFilename();
					String path = request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로 
					String fpath = path + "\\" + filename;
					System.out.println(fpath);
					
					if(!filename.equals("")) {  //실 파일 업로드 (웹서버)
						FileOutputStream fs =null;
						try {
							     fs = new FileOutputStream(fpath);
							     fs.write(mutifile.getBytes());
			     
							     filenames.add(filename);  //db에 입력될 파일명
							     
						} catch (Exception e) {
							e.printStackTrace();
						}finally {
							 try {
								fs.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
		}
		 
			//파일명 (DTO)
			n.setFileSrc(filenames.get(0));
			n.setFileSrc2(filenames.get(1));
			try {
				noticedao.update(n);  //DB update
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		//처리가 끝나면 상세 페이지로 : redirect  글번호를 가지고 ....
		return "redirect:noticeDetail.htm?seq="+n.getSeq();    //서버에게 새 요청 ....
	}
	
	//삭제하기
	@RequestMapping("noticeDel.htm")
	public String noticeDel(String seq) {
		try {
			noticedao.delete(seq);
		} catch (Exception e) {
		}
		
		return "redirect:notice.htm";
	}
}
