package com.ezen.biz.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.ezen.biz.dto.UserVO;

public class UserServiceClient {

	public static void main(String[] args) {
		// 스프링 컨테이너 구동
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		// UserServiceImpl 객체 Lookup
		UserService userService = (UserService)container.getBean("userService");
		
		// 로그인 기능 테스트
		UserVO user = new UserVO();
		user.setId("user1");
		user.setPassword("user1");
		
		UserVO result = userService.getUser(user);
		if(result != null) {
			System.out.println("로그인에 성공하였습니다.");
			System.out.println("사용자 정보: " + result);
			
		}else {
			System.out.println("로그인에 실패하였습니다.");
		}

	}

}
