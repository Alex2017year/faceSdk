package test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.IAccountService;

public class TestAccount {

    @Test
    public void testTransform() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        IAccountService accountService = (IAccountService) applicationContext.getBean("accountService");
        accountService.transfer("jack", "rose", 1000);
    }

    @Test
    public void testTransform2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext2.xml");
        IAccountService accountService = (IAccountService) applicationContext.getBean("proxyService");
        accountService.transfer("jack", "rose", 1);

    }
}
