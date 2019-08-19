package demo.aop;

public class MyAspect {
    public void before() {
        System.out.println("开始事务...");
    }

    public void after() {
        System.out.println("提交事务...");
    }
}
