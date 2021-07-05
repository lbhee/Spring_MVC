package com.model;

//DB에 있다고 가정하고 1:1로 매핑되는 클래스(DTO)
//DB의 컬럼명, DTO의 멤버필드명, form의 name값을 동일하게해서 자동매핑
public class NewArticleCommand {
	private int parentId;
	private String title;
	private String content;
	
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "NewArticleCommand [parentId=" + parentId + ", title=" + title + ", content=" + content + "]";
	}

}
