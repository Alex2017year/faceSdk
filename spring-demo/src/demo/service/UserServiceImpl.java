package demo.service;

import demo.User;
import demo.domain.IUserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// 相当于在applicationContext.xml配置了 <bean class="demo.service.UserServiceImpl"></bean>
// 默认情况下注解是不生效的，如何开启？在applicationContext.xml添加
@Data
@Service("userService")
public class UserServiceImpl implements IUserService {

    // Spring 会自动找到IUserRepository的实现类型，并注入到userRepository属性里面去
    @Autowired
    private IUserRepository userRepository;

    @Override
    public void add(User user) {
        // System.out.println("add a new user : " + user);
        userRepository.add(user);
    }

    @Override
    public void delete() {
        System.out.println("delete a user!");
    }
}
