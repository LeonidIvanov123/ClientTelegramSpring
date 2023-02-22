package ru.leonid.ClientTelegramSpring.Service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BotServiceAspect {
    @Before("execution(* ru.leonid.ClientTelegramSpring.Service.BotService.*(..))") //forEach method from BotService.java
    public void checkExecutionMethod(JoinPoint jp) throws Throwable{
        System.out.println("***Executing method: " + jp.toString());
    }
}