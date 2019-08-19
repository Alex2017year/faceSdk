package test;

import demo.springaop.ICustomerService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAspect {

    @Test
    public void testBeforeMethod() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext7.xml");
        ICustomerService customerService = (ICustomerService) applicationContext.getBean("customerService");
        customerService.addCustomer();
    }
}
