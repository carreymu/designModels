package models.visitor;

public abstract class Person {
    // 通过可以让访问者访问的方法
    abstract void accept(Action action);
}
