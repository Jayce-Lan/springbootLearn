package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//切面类
@Component
@Aspect
public class LogAspect {
    /**
     * 定义切入点
     * 第一个 * 表示的是方法返回任意值
     * 第二个 * 表示service包的任意类
     * 第三个 * 表示类中的任意方法
     * (..) 表示方法的任意参数
     */
    @Pointcut("execution(* com.example.service.*.*(..))")
    public void pc1() {
    }

    @Before(value = "pc1()")
    public void before(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("AOP介入 >>> " + name + "方法开始执行...");
    }

    @After(value = "pc1()")
    public void after(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("AOP介入 >>> " + name + "方法执行结束...");
    }

    /**
     * 返回通知
     * 可以获取目标方法的返回值
     *
     * @param joinPoint 可以获取目标方法名、修饰符等信息
     * @param result
     */
    @AfterReturning(value = "pc1()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        System.out.println("AOP介入 >>> " + name + "方法返回值为 >>> " + result);
    }

    /**
     * 异常通知，可以获取目标方法的返回值
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "pc1()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String name = joinPoint.getSignature().getName();
        System.out.println("AOP介入 >>> " + name + "方法异常，异常为 >>> " + e);
    }

    /**
     * 环绕通知
     *
     * @param pjp 调用其 proceed 方法使得目标方法继续执行
     * @return
     * @throws Throwable
     */
    @Around("pc1()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }
}
