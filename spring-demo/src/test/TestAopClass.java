package test;

import demo.aop.IEmployeeService;
import demo.aop.MyBeanFactory;
import org.junit.Test;

public class TestAopClass {


    @Test
    public void testProxy() {

        IEmployeeService employeeService = MyBeanFactory.createEmployeeService();
        employeeService.deleteEmployee(10);
    }

    @Test
    public void testCglib() {
        IEmployeeService employeeService = MyBeanFactory.CreateEmployeeService();
        employeeService.addEmployee();
    }

}
