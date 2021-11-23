package models.visitor;

/**
 * 双分派
 * 1. 在客户端程序中,将具体状态作为参数传递Man中
 * 2. Man类调用作为参数的方法getManResult将this作为参数传入
 */
public class Man extends Person {
    @Override
    void accept(Action action) {
        action.getManResult(this);
    }
}
