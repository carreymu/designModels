package models.flyweight;

public class ConcreteWebsite extends Website {
    // 共享部分
    private String type = ""; // 网站发布形式(类型)

    public ConcreteWebsite(String type) {
        this.type = type;
    }

    @Override
    void use() {
        System.out.println("网站发布形式：" + type);
    }
}
