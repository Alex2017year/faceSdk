package demo.web;

import demo.User;
import demo.service.IUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
@Scope("prototype") // 表示多例
public class UserRestController {

    /*
        Autowired 是根据类型注入值：根据接口类型，找到实现类；如果是类，就找到对应的类。
     */
    // @Autowired
    // @Qualifier("userService") // 根据指定id注入属性 ---- 用的比较少
    @Resource(name = "userService") // 这一行代码等效于前面两行代码   --- 用的比较少
    private IUserService userService;

    public void save(User user) {
        System.out.println("user rest controller save()...");
        userService.add(user);
    }
}


