package edu.kh.project.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class AroundAspect {
	
	@Around("CommonPointcut.serviceImplPointcut()")
	public Object aroundServiceLog(ProceedingJoinPoint pj) throws Throwable {
		// @Around advice는 JoinPoint Interface가 아닌
		// 하위 타입인 ProceedingJoinPoint를 사용 해야함
		
		long startMs = System.currentTimeMillis(); //서비스 시작 시의 ms 값
		
		Object obj=pj.proceed(); // 여기가 기준
		
		long endMs = System.currentTimeMillis(); //서비스 종료 시의 ms 값
		
		String str = "Running Time : " + (endMs - startMs) + "ms";
		
		log.info(str);
		
		return obj;
		
	}

}
