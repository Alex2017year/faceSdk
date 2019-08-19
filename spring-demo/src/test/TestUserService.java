package test;

import demo.User;
import demo.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserService {

    @Test
    public void testAddUser() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext3.xml");

        // 如果@Component没有配置id，可以通过类型获取
        // 当然如果配置了id，也可以通过id来获取
        // IUserService userService = applicationContext.getBean(UserServiceImpl.class);
        // IUserService userService = applicationContext.getBean(IUserService.class); // 这里面的类型，可以是接口，也可以是实现类 --- 牛逼
        IUserService userService = (IUserService) applicationContext.getBean("userService");
        userService.add(new User("Alex"));
    }
}
