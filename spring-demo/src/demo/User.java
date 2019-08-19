package demo;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public User() {
        // System.out.println("bean creates a user..");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void testUser() {
        System.out.println("user name: " + name);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
