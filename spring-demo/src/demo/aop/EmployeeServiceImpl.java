package demo.aop;

public class EmployeeServiceImpl implements IEmployeeService {
    @Override
    public void addEmployee() {
        System.out.println("add a new Employee ...");
    }

    @Override
    public void updateEmployee() {
        System.out.println("update a new Employee ...");
    }

    @Override
    public int deleteEmployee(int id) {
        System.out.println("delete a new Employee ...");
        return id;
    }
}
