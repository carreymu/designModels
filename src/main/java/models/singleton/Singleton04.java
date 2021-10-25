package models.singleton;

/**
 * 优点：懒加载，线程安全
 * 缺点：synchronized在方法上，每个线程都执行，效率不高
 */
public class Singleton04 {
    public static void main(String[] args) {
        System.out.println("----懒汉式(线程安全)");
        Singleton4 instance = Singleton4.getInstance();
        Singleton4 instance1 = Singleton4.getInstance();
        String result = instance == instance1 ? "True" : "false";
        System.out.println("instance == instance1 ? " + result);
        System.out.println("------hashCode");
        System.out.println("instance.hashCode = " + instance.hashCode());
        System.out.println("instance1.hashCode = " + instance1.hashCode());
    }
}

/**
 * 懒汉式(线程安全)
 */
class Singleton4 {
    private static Singleton4 instance;

    private Singleton4() {
    }

    // 需要时才创建
    public static synchronized Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
