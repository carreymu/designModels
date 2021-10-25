package models.singleton;

/**
 * 优点：线程安全，懒加载(推荐)
 */
public class Singleton06 {
    public static void main(String[] args) {
        System.out.println("----静态内部类");
        Singleton6 instance = Singleton6.getInstance();
        Singleton6 instance1 = Singleton6.getInstance();
        String result = instance == instance1 ? "True" : "false";
        System.out.println("instance == instance1 ? " + result);
        System.out.println("------hashCode");
        System.out.println("instance.hashCode = " + instance.hashCode());
        System.out.println("instance1.hashCode = " + instance1.hashCode());
    }
}

/**
 * 静态内部类
 */
class Singleton6 {
    private static volatile Singleton6 instance;

    private Singleton6() {
    }

    // 1.当Singleton6被装载时,Singleton6Instance并不会被立即装载
    // 2.当调用getInstance时，静态内部类Singleton6Instance才会被初始化,并且仅被装载一次
    public static class Singleton6Instance{
        private static final Singleton6 INSTANCE = new Singleton6();

    }

    public static Singleton6 getInstance(){
        return Singleton6Instance.INSTANCE;
    }
}
