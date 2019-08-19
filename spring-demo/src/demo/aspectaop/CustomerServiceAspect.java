package demo.aspectaop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class CustomerServiceAspect implements MethodInterceptor {

    // 拦截方法...
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        System.out.println("拦截方法...");
        System.out.println("开启事务");

        // 放行，执行方法
        Object ret = methodInvocation.proceed();
        System.out.println("提交事务");

        return ret;
    }
}
