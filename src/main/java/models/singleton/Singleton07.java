package models.singleton;

/**
 * 优点: 避免多线程同步问题，还可以防止反序列化重新创建新对象(推荐使用)
 */
public class Singleton07 {
    public static void main(String[] args) {
        System.out.println("----枚举方式");
        Singleton7 instance = Singleton7.INSTANCE;
        Singleton7 instance1 = Singleton7.INSTANCE;
        String result = instance == instance1 ? "True" : "false";
        System.out.println("instance == instance1 ? " + result);
        System.out.println("------hashCode");
        System.out.println("instance.hashCode = " + instance.hashCode());
        System.out.println("instance1.hashCode = " + instance1.hashCode());

        instance.saySomething();
    }
}

/**
 * 枚举方式
 */
enum Singleton7 {
    INSTANCE; // 属性

    public void saySomething() {
        System.out.println("hi~~");
    }
}