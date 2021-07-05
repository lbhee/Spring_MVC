package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.OrderCommand;

/*
	하나의 요청주소로 2가지 업무처리
	/order/order.do
	화면보여주기(GET), 처리하기(POST)
	
	
*/

@Controller
@RequestMapping("/order/order.do")
public class OrderController {
	
	@RequestMapping(method = RequestMethod.GET) //post라고 명시하지 않으면 get방식!
	public String form() {
		return "order/OrderForm"; //뷰의 주소리턴하면 리저버가 실주소를 만든다.
		// /WEB-INF/views/order/OrderForm.jsp
	}
	
	@RequestMapping(method = RequestMethod.POST) 
	public String submit(OrderCommand ordercommand) { //DTO타입으로 명시하면
		/*
		 * 자동화되는 작업!
		OrderCommand ordercommand = new OrderCommand();
	                 -> private List<OrderItem> orderItem;
	                 
		List<OrderItem> itemlist = new ArrayList<>();
		list.add(new OrderItem(1,10,"파손주의");
		list.add(new OrderItem(10,1,"리모콘별도구매");
		
		ordercommand.setOrderItem(itemlist);
		
		 forwoad 객체 key  >> orderCommand  --> 자동으로 앞글자만 소문자로해서 만들어진다.
		*/

		return "order/OrderCommited";
	}
}
