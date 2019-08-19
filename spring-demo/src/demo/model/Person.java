package demo.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

public class Person implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("2. 对象属性赋值...");
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person() {
        System.out.println("1. Person 对象实例化....");
    }

    public void myInit() {
        System.out.println("7. 自定义初始化方法...");
    }

    public void myDestroy() {
        // 用于资源释放...
        System.out.println("10. 自定义销毁方法...");
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("3. 设置bean名字： " + s); // 也就是applicationContext.xml Bean的id
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("4. bean 工厂： " + beanFactory); // 把对象放进Bean工厂（也就是容器里面）
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("6. 属性赋值完成...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("9. bean 对象销毁");
    }
}
