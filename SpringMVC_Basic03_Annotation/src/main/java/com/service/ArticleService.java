package com.service;

import com.model.NewArticleCommand;

/*
	@Service 어노테이션을 붙이고,
	dispater-servlet.xml상단에
	<context:component-scan base-package="com.service"> 써주면
	자동으로 객체를 생성해준다.
*/


public class ArticleService {
	public ArticleService() {
		System.out.println("ArticleService 생성자 호출");
	}
	
	public void writeAritcle(NewArticleCommand command) {
		//DAO가 있다고 가정하고
		//insert가 실행되었다고 가정하고
		System.out.println("글쓰기 작업 완료 : " + command.toString());
	}
}

