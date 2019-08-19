package demo.aspectaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    // 前置通知
    @Before("execution(* demo.springaop.CustomerServiceImpl.*(..))")
    public void beforeMethod(JoinPoint jp) {
        System.out.println("前置通知...." + jp.getSignature().getName()); // 连接点的方法名
    }

    /**
     * 后置通知获取service 方法执行后的返回值
     * Object retVaule ：表示service 方法调用的返回值，同时需要在xml中配置returning
    * */
    @AfterReturning(pointcut = "comPointcut()", returning = "retValue")
    public void afterMethodReturning(JoinPoint jp, Object retValue) {
        System.out.println("后置通知....返回值：" + retValue);
    }

    @Around("comPointcut()")
    public Object arroundMethod(ProceedingJoinPoint pjp) throws Throwable { // 连接点作为参数
        System.out.println("环绕通知....");
        System.out.println(pjp.getSignature().getName()); // 切入点的方法名

        System.out.println("开启事务...");

        // 放行
        Object ret = pjp.proceed(); // 运行方法

        System.out.println("提交事务...");
        return ret;
    }

    @AfterThrowing(pointcut = "comPointcut()", throwing = "e")
    public void afterThrowing(JoinPoint jp, Throwable e) {
        System.out.println("连接方法："+ jp.getSignature().getName() +" 异常: " + e.getMessage());
    }

    @After("comPointcut()")
    public void afterMethod(JoinPoint jp) {
        System.out.println("最终通知...");
    }

    // 声明公共的切入点
    @Pointcut("execution(* demo.springaop.CustomerServiceImpl.*(..))")
    public void comPointcut(){}
}
