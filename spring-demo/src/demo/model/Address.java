package demo.model;

import lombok.Data;

@Data
public class Address {
    private String addressName;

    @Override
    public String toString() {
        return "Address{" +
                "addressName='" + addressName + '\'' +
                '}';
    }
}
