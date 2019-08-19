package demo.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

// 针对所有对象有效
public class MyBeanProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {

        // 可用于多个对象处理相同的事务....
        System.out.println("5.预处理: " + o + ":" + s);
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("8.后处理: " + o + ":" + s);
        return o;
    }
}
