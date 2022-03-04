package org.project.my.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//aop구현
@Component //스프링이 자동객체 생성
@Aspect
public class LoggingAdvice {
	//org.company.app.controller하위의 모든 클래스와 메소드를 실행하기 전에
	@Before("execution(* org.project.my.*.*.*(..))")
	public void beforeLog(JoinPoint jp) {
		//적용대상(JointPoint)의 메소드명 + 매개변수를 출력
		System.out.println(jp.getSignature().toShortString()+":"+Arrays.toString(jp.getArgs()));
	}
	
	//정상 수행 후 리턴값 출력
	@AfterReturning(pointcut = "execution(* org.project.my.*.*.*(..))", returning = "rObj")
	public void afterLog(JoinPoint jp, Object rObj) {
		System.out.println(jp.getSignature().toShortString()); //실행된 메소드명
		if(rObj!=null) {			
			System.out.println("리턴값: " + rObj.toString());
		}else {
			System.out.println("리턴값: 없음");
		}
	}	
	
	//실행대상을 시작과 끝을 체크
//	@Around("execution(* org.spring.myapp.service.*.*(..))")
//	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
//		//시작시간
//		long startTime = System.currentTimeMillis();
//		System.out.println(pjp.getSignature().toShortString());
//		Object result = pjp.proceed();
//		//끝시간
//		long endTime = System.currentTimeMillis();
//		System.out.println("소요시간: " + (endTime - startTime));
//		return result;
//	}
}
