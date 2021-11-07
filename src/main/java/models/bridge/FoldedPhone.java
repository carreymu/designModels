package models.bridge;

// 折叠手机类，继承抽象Phone
public class FoldedPhone extends Phone {
    public FoldedPhone(Brand brand) {
        super(brand);
    }

    public void open() {
        super.open();
        System.out.println("折叠手机 biubiubiu...");
    }

    public void close() {
        super.close();
        System.out.println("折叠手机 biubiubiu...");
    }

    public void call() {
        super.call();
        System.out.println("折叠手机 biubiubiu...");
    }
}
