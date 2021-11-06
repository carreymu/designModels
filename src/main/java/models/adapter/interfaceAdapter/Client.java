package models.adapter.interfaceAdapter;

public class Client {
    public static void main(String[] args) {
        AbsAdapter absAdapter = new AbsAdapter() {
            @Override
            public void m1() {
                System.out.println("我只在乎你，m1.");
            }
        };
        absAdapter.m1();
        System.out.println("======");
        absAdapter.m3();
    }
}
