package models.singleton;

/**
 * 优点：懒加载，线程不安全
 * 缺点：当A执行完if之后在new之前，线程B执行了if完成；此时出现了俩实例
 */
public class Singleton03 {
    public static void main(String[] args) {
        System.out.println("----懒汉式(线程不安全)");
        Singleton3 instance = Singleton3.getInstance();
        Singleton3 instance1 = Singleton3.getInstance();
        String result = instance == instance1 ? "True" : "false";
        System.out.println("instance == instance1 ? " + result);
        System.out.println("------hashCode");
        System.out.println("instance.hashCode = " + instance.hashCode());
        System.out.println("instance1.hashCode = " + instance1.hashCode());
    }
}

/**
 * 懒汉式(线程不安全)
 */
class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {
    }

    // 需要时才创建
    public static Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}