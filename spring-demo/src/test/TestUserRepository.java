package test;

import demo.User;
import demo.web.UserRestController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class TestUserRepository {

    @Test
    public void testAdd() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext3.xml");
        UserRestController userRestController = applicationContext.getBean(UserRestController.class);
        User user = new User("AlexBin");
        userRestController.save(user);

        applicationContext.getClass().getMethod("close").invoke(applicationContext);

    }
}
