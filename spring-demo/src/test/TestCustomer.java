package test;

import demo.model.Customer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCustomer {

    @Test
    public void testCustomer() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Customer customer = (Customer) applicationContext.getBean("customer");

        System.out.println(customer);
    }
}
