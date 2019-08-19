package service.impl;

import dao.IAccountDao;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import service.IAccountService;

public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    // 由spring 配置事务模板
    private TransactionTemplate transactionTemplate;

    @Override
    public void transfer(String outer, String inner, Integer money) {

        this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                // 出账
                accountDao.in(inner, money);

                // 进账
                accountDao.out(outer, money);
            }
        });

    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public IAccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
