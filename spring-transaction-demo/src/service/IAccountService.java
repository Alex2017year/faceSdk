package service;

public interface IAccountService {
    /**
     * 转账操作
     * @param outer -- 转出账户
     * @param inner -- 转入账户
     * @param money -- 转入/转出金额
     */
    public void transfer(String outer, String inner, Integer money);
}
