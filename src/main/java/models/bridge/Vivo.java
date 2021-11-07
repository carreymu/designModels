package models.bridge;

public class Vivo implements Brand{
    @Override
    public void open() {
        System.out.println("vivo开机无广告");
    }

    @Override
    public void close() {
        System.out.println("vivo关机");
    }

    @Override
    public void call() {
        System.out.println("vivo打电话");
    }
}
