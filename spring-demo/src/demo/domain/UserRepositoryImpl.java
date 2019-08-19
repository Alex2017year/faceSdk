package demo.domain;

import demo.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    @PostConstruct
    public void init() {
        System.out.println("UserRepositoryImpl init...");
    }

    @Override
    public void add(User user) {
        System.out.println("add a new user: " + user);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("UserRepositoryImpl destructor...");
    }
}

