package demo.dao;

import demo.model.User;

public interface IUserDao {
    void addUser(User user);
    void deleteUser(int id);
}
