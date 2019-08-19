package test;

import demo.springaop.ICustomerService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAutomaticSpringAop {

    @Test
    public void testAutomaticAop() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext5.xml");

        ICustomerService customerService = (ICustomerService) applicationContext.getBean("customerService");
        customerService.updateCustomer();
    }
}
