package demo.springaop;

import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerServiceImpl implements ICustomerService {

    @Override
    public void addCustomer() {
        System.out.println("add a customer ...");
        // throw new ArithmeticException("测试异常");
    }

    @Override
    public void updateCustomer() {
        System.out.println("update a customer ...");
    }

    @Override
    public void deleteCustomer() { System.out.println("delete a customer ...");
    }
}
