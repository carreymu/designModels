package models.singleton;

/**
 * 优点：比较简单
 * 缺点：无法懒加载，可能造成内存浪费
 */
public class Singleton01 {
    public static void main(String[] args) {
        System.out.println("---饿汉式(静态变量)");
        Singleton1 instance = Singleton1.getInstance();
        Singleton1 instance1 = Singleton1.getInstance();
        String result = instance == instance1 ? "True" : "false";
        System.out.println("instance == instance1 ? " + result);
        System.out.println("------hashCode");
        System.out.println("instance.hashCode = " + instance.hashCode());
        System.out.println("instance1.hashCode = " + instance1.hashCode());
    }
}

/**
 * 饿汉式(静态变量)
 */
class Singleton1 {
    // 1. 构筑起私有化,外部new
    private Singleton1() {

    }

    // 2.本类内部创建对象实例
    private final static Singleton1 instance = new Singleton1();

    // 3.提供一个公有的静态方法,返回实例对象
    public static Singleton1 getInstance() {
        return instance;
    }
}
