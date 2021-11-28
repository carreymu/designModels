package models.memento;

public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setState("State #1 force 100");

        // 保存当前状态
        caretaker.add(originator.saveStateMemento());
        originator.setState("State #2 force 20");
        caretaker.add(originator.saveStateMemento());

        originator.setState("State #3 force 1");
        caretaker.add(originator.saveStateMemento());

        originator.display();
//        System.out.println("Current State" + originator.getState());
        System.out.println("=====recover....");
        // 恢复状态1
        originator.getStateFromMemento(caretaker.get(0));
//        System.out.println("Recover State result" + originator.getState());
        originator.display();
    }
}
