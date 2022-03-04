package org.project.my.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

//컨트롤러에서 발생하는 예외를 전문적으로 처리하는 클래스
@ControllerAdvice
public class CommonExceptionAdvice {
//	//Exception 예외발생시 처리해줄 메소드
//	@ExceptionHandler(Exception.class)
//	public String common(Exception e) {
//		System.out.println(e.toString());
//		//사용자에게 보여줄 화면으로 이동
//		return "error/errorCommon";
//	}
//	
//	@ExceptionHandler(NoHandlerFoundException.class)
//	public String common404() {
//		return "error/error404";
//	}
}
