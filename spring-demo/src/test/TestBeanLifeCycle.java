package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class TestBeanLifeCycle {


    @Test
    public void testPerson() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 测试Bean生命周期
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // Person person = (Person) applicationContext.getBean("person");

        // System.out.println(person);

        // 关闭Spring容器 -- 这就是一种反射
        applicationContext.getClass().getMethod("close").invoke(applicationContext);
    }
}
