package test;

import demo.CreateUserFactory;
import demo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestUser {

    @Test
    public void testMethod() {

        // Spring 容器加载的三种方式
        // 1. 使用 ClassPathXmlApplicationContext -- 类路径加载，也就是放在src目录下 [一定掌握]
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml"); // 类路径指的就是src路径 - 打包之后就成了class路径

        // 2. 使用 文件系统路径获取配置文件[绝对路径]
        // ApplicationContext applicationContext = new FileSystemXmlApplicationContext("F:\\IntelljWorkSpace\\spring-demo\\src\\applicationContext.xml");

        // 3. 使用BeanFactory(仅作了解)
        // BeanFactory factory = new XmlBeanFactory(new FileSystemResource("F:\\IntelljWorkSpace\\spring-demo\\src\\applicationContext.xml"));

        // 以上三种方法的区别
        /*
        * BeanFactory : 采用延迟加载，第一次getBean时才会初始化Bean
        * ApplicationContext 即时加载
        * ApplicationContext 对 BeanFactory 扩展，提供更多功能： 国际化处理；事件传递；Bean 自动装配； 各种不同应用层的Context的实现
        * */

        // User user = (User)factory.getBean("user");
        // user.testUser();
    }


    @Test
    public void testStaticMethod() {
        // User user = CreateUserFactory.createUser();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml"); // 类路径指的就是src路径 - 打包之后就成了class路径
        User user1 = (User)applicationContext.getBean("instance_user");
        user1.testUser();
    }

    @Test
    public void testBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml"); // 类路径指的就是src路径 - 打包之后就成了class路径
        User user1 = (User)applicationContext.getBean("user");
        User user2 = (User)applicationContext.getBean("user");

        System.out.println(user1);
        System.out.println(user2);

    }

}
