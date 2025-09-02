package com.s2p.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
public class CourseFeeLoggerAspect {

    @Around("execution(* com.s2p.services.CourseFeeService.*(..))")
        public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

            Instant startTime = Instant.now();
            System.out.println(joinPoint.getSignature().getName() + " method started at : " + startTime);

            Object response = joinPoint.proceed();

            Instant endTime = Instant.now();
            System.out.println(joinPoint.getSignature().getName() + " method finished at : " + endTime);
            System.out.println(joinPoint.getSignature().getName() + " method time elapsed : " + Duration.between(startTime, endTime));

            return response;
        }
    }

