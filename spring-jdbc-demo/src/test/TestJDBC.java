package test;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import demo.dao.IUserDao;
import demo.model.User;


public class TestJDBC {

    @Test
    public void testDatabse() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_data");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        // 创建 jdbc template
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("update user_table set username=? where id=?", "betty", 1);

    }

    @Test
    public void testJDBC() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserDao userDao = (IUserDao) applicationContext.getBean("userDao");
        userDao.addUser(new User("sunny", "14566"));
    }
}
