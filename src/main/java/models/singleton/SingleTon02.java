package models.singleton;

/**
 * 优点：比较简单
 * 缺点：无法懒加载，可能造成内存浪费
 */
public class SingleTon02 {
    public static void main(String[] args) {
        Singleton2 instance = Singleton2.getInstance();
        Singleton2 instance1 = Singleton2.getInstance();
        String result = instance == instance1 ? "True" : "false";
        System.out.println("instance == instance1 ? " + result);
        System.out.println("------hashCode");
        System.out.println("instance.hashCode = " + instance.hashCode());
        System.out.println("instance1.hashCode = " + instance1.hashCode());
    }
}


/**
 * 饿汉式(静态代码块)
 */
class Singleton2 {
    // 1. 构筑起私有化,外部new
    private Singleton2() {

    }

    // 2.本类内部创建对象实例
    private  static Singleton2 instance;

    static {
        instance = new Singleton2();
    }
    // 3.提供一个公有的静态方法,返回实例对象
    public static Singleton2 getInstance() {
        return instance;
    }
}