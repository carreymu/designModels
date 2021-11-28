package models.memento;

public class Originator {
    // 状态信息
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // 保存状态对象Memento
    public Memento saveStateMemento() {
        return new Memento(state);
    }

    // 通过备忘录对象回复状态
    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }

    public void display() {
        System.out.println("current state" + this.state);
    }
}
