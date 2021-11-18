package models.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {

        // 1. ClassLoader: 指定目标对象使用的类加载器
        // 2. Class<?>[]:目标对象实现的接口类型，使用泛型方法确认类型
        // 3. InvocationHandler:事情处理,执行目标对象方法时，会厨房事情处理器方法
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("代理调用开始。。。");
                        Object val = method.invoke(target, args);
                        System.out.println("代理调用结束。。。");
                        return val;
                    }
                });
    }
}
