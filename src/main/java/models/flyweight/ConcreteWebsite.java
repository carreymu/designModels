package models.flyweight;

public class ConcreteWebsite extends Website {
    private String type = ""; // 网站发布形式(类型)

    public ConcreteWebsite(String type) {
        this.type = type;
    }

    @Override
    void use() {
        System.out.println("网站发布形式：" + type);
    }
}
