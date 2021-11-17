package models.flyweight;

public class ConcreteWebsite extends Website {
    // 共享部分,内部状态
    private String type = ""; // 网站发布形式(类型)

    public ConcreteWebsite(String type) {
        this.type = type;
    }

    @Override
    void use(User user) {
        System.out.println("网站发布形式：" + type + "，使用者" + user.getName());
    }
}
