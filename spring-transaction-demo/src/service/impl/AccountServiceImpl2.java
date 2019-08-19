package service.impl;

import dao.IAccountDao;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import service.IAccountService;

public class AccountServiceImpl2 implements IAccountService {

    private IAccountDao accountDao;


    @Override
    public void transfer(String outer, String inner, Integer money) {
        // 出账
        accountDao.in(inner, money);

        // 进账
        accountDao.out(outer, money);
    }


    public IAccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
