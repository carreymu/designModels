package models.bridge;

public class UprightPhone extends Phone {
    public UprightPhone(Brand brand) {
        super(brand);
    }

    protected void open() {
        System.out.println("所有直板打开,请先点亮屏幕...");
        super.open();
        System.out.println("直板手机 dadada>>>");
    }

    protected void call() {
        super.call();
        System.out.println("直板手机 dadada>>>");
    }

    protected void close() {
        super.close();
        System.out.println("直板手机 dadada>>>");
    }
}
