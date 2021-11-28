package models.mediator;

public abstract class Mediator {
    // 将中介者对象加入到集合中
    abstract void Register(String colleagueName, Colleague colleague);

    // 接收消息，具体同时对象发出
    abstract void getMessage(int stateChange, String colleague);

    abstract void sendMessage();
}
