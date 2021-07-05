package com.model;

import java.util.List;

//주문서(내역) 클래스
//하나의 주문은 여러개의 상품을 가질 수 있다.

//***********************************
//board와 reply와의 관계
//(하나의 게시판은 여러개의 덧글을 가질 수 있다.)
//***********************************

//***********************************
//하나의 은행은 여러개의 계좌를 가질 수 있다.
//***********************************

public class OrderCommand {
	private List<OrderItem> orderItem; //key point!

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
	
	
}

/*
	<java코드>
	
	- 주문발생
	OrderCommand ordercommand = new OrderCommand();
	
	List<OrderItem> itemlist = new ArrayList<>();
	list.add(new OrderItem(1,10,"파손주의");
	list.add(new OrderItem(10,1,"리모콘별도구매");
	
	ordercommand.setOrderItem(itemlist);
*/
