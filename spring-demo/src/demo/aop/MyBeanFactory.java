package demo.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanFactory {

    /**
     * 使用JDK实现AOP编程
     * @return
     */
    public static IEmployeeService createEmployeeService() {

        // 创建目标对象target
        IEmployeeService employeeService = new EmployeeServiceImpl();

        // 声明切面对象
        MyAspect myAspect = new MyAspect();

        // 把切面类2个方法应用到目标类
        IEmployeeService employeeServiceProxy = (IEmployeeService) Proxy.newProxyInstance(
                // 参数1：loader,类加载器,动态代理类，运行时创建，如何类都需要类加载器将其加载到内存
                // 一般情况：当前类.class.getClassLoader();目标类实例.getClass().getClassLoader();
                MyBeanFactory.class.getClassLoader(),
                // 参数2：Class[] interfaces 代理类需要实现的所有接口
                // 方式1：目标类实例.getClass.getInterfaces();注意：只能获得自己的接口，不能获得父类元素接口
                // 方式2：new class[]{UserService.class}
                employeeService.getClass().getInterfaces(),
                // 参数3：InvocationHandler() 处理类，接口，必须进行实现类，一般采用匿名内部
                // 提供invoke方法，代理类的每一个方法执行时，都将调用一次invoke
                // 参数31：Object proxy:代理对象
                // 参数32：Method method:代理对象当前执行的方法的描述对象（反射）
                // 参数33:Object[] args:方法实际参数
                (Object proxy, Method method, Object[] args) -> {
                    // 开启事务
                    myAspect.after();

                    Object ret = method.invoke(employeeService,args);
                    // 返回值对象--> 业务方法的返回值
                    System.out.println("拦截返回值：" + ret);

                    myAspect.before();
                    return ret;
                });


        return employeeServiceProxy;
    }


    /**
     * 使用cglib实现AOP编程
     * cglib 的代理实现与接口无关，JDK的代理必须要用到接口；而cglib 没有这个限制。
     * cglib 实现原理猜测：应该是把实现类作为新生成代理类的父类，接着重写了实现类的各种方法，
     *                  在重新方法中调用intercept方法。
     */
    public static IEmployeeService CreateEmployeeService() {

        // 目标类
        IEmployeeService employeeService = new EmployeeServiceImpl();

        // 切面类
        MyAspect aspect = new MyAspect();

        // cglib 类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(employeeService.getClass()); // 实现类 作为 父类！
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                aspect.before();

                // 两种可选方法实现拦截方法的执行
                // Object retObj = methodProxy.invoke(employeeService, args);
                Object obj = methodProxy.invokeSuper(proxy, args); // 这种做法解耦，因为不需要上面的employeeService
                aspect.after();
                return obj;
            }
        });

        // 创建代理对象
        EmployeeServiceImpl proxy = (EmployeeServiceImpl) enhancer.create();
        return proxy;
    }


}
