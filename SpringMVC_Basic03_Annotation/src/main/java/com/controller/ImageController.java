package com.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.model.Photo;

@Controller
@RequestMapping("/image/upload.do")
public class ImageController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return "image/image";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(Photo photo, HttpServletRequest request) {
		
		/*
			1. Photo DTO 타입으로 데이터 받기
			1.1 자동화 : name 속성값이 Photo타입 클래스 memberfield명과 동일해야함
			2. 내부적으로 public String submit(Photo photo) 이 코드가 자동으로!
			   Photo photo = new Photo(); 객체생성
			   photo.setName("홍길동"); 주입
			   photo.setAge(10); 주입
			   photo.setImage(); >> 자동주입안됨 >> 업로드한 파일명을 가공작업이 필요함. > CommonsMultipartFile 이름추출
			   photo.setFile(CommonsMultipartFile file);
			   
	   		   System.out.println(photo.toString());
			   --> Photo [name=홍길동, age=10, image=null, file=org.springframework.web.multipart.commons.CommonsMultipartFile@73cd0868]
		*/
		CommonsMultipartFile imagefile = photo.getFile();
		
		System.out.println("imagefile.name : " + imagefile.getName());
		System.out.println("imagefile.ContentType : " + imagefile.getContentType());
		System.out.println("imagefile.OriginalFilename : " + imagefile.getOriginalFilename());
		System.out.println("imagefile.Bytes : " + imagefile.getBytes().length);
		
		//POINT
		//DB에 들어갈 파일명 추출
		photo.setImage(imagefile.getName());

		//cos.jar 자동파일업로드가됬었지만, 
		//실제파일업로드구현(upload폴더에 업로드)
		String filename = imagefile.getOriginalFilename(); //첨부파일이름
		String path = request.getServletContext().getRealPath("/upload"); //배포된 서버 경로 
		String fpath = path + "\\" + filename;
		
		FileOutputStream fs = null;
		try {
			     fs = new FileOutputStream(fpath);
			     fs.write(imagefile.getBytes());
			     
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//DB작업 .... 파일 업로드 완료
		return "image/image";
	}
}
