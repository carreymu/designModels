package models.bridge;

public class XiaoMi implements Brand {
    @Override
    public void open() {
        System.out.println("进入开机广告");
        System.out.println("xiaomi开机");
    }

    @Override
    public void close() {
        System.out.println("xiaomi关机");
    }

    @Override
    public void call() {
        System.out.println("xiaomi打电话");
    }
}
