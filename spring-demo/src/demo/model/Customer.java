package demo.model;

import lombok.Data;

@Data
public class Customer {
    private String name;
    private String gender;
    private double salary;
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", salary=" + salary +
                ", address=" + address +
                '}';
    }
}
