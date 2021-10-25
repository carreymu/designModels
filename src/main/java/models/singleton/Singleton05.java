package models.singleton;

/**
 * 优点：线程安全，懒加载，效率高(推荐使用)
 */
public class Singleton05 {
    public static void main(String[] args) {
        System.out.println("----双重检查");
        Singleton5 instance = Singleton5.getInstance();
        Singleton5 instance1 = Singleton5.getInstance();
        String result = instance == instance1 ? "True" : "false";
        System.out.println("instance == instance1 ? " + result);
        System.out.println("------hashCode");
        System.out.println("instance.hashCode = " + instance.hashCode());
        System.out.println("instance1.hashCode = " + instance1.hashCode());
    }
}

/**
 * 双重检查
 */
class Singleton5 {
    private static volatile Singleton5 instance;

    private Singleton5() {
    }

    // 需要时才创建
    public static Singleton5 getInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
