package dao.impl;

import dao.IAccountDao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class AccountDaoImpl extends JdbcDaoSupport implements IAccountDao {

    @Override
    public void out(String outer, Integer money) {
        getJdbcTemplate().update("update account set money=money-? where username=?", money, outer);
    }

    @Override
    public void in(String inner, Integer money) {
        getJdbcTemplate().update("update account set money=money+? where username=?", money, inner);
    }
}
