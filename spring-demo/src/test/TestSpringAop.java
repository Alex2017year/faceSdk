package test;

import demo.springaop.ICustomerService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringAop {

    @Test
    public void testSpringAop() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext4.xml");
        ICustomerService customerService = (ICustomerService) applicationContext.getBean("serviceProxy");
        customerService.addCustomer();
    }

}
