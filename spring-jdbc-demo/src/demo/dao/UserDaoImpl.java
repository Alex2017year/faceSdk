package demo.dao;

import demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements IUserDao {

    // 除了组合一个jdbcTemplate 这个对象外,我们还可以通过继承 JdbcDaoSupport 这个类,这个类有个JdbcTemplate属性
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addUser(User user) {
        jdbcTemplate.update("insert user_table (username, password) values (?,?)", user.getUsername(), user.getPassword());
    }

    @Override
    public void deleteUser(int id) {

    }
}
