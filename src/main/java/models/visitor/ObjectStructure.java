package models.visitor;

import java.util.LinkedList;
import java.util.List;

public class ObjectStructure {
    // 维护一个Person集合
    private List<Person> personList = new LinkedList<>();

    public void attach(Person person) {
        personList.add(person);
    }

    public void detach(Person person) {
        personList.remove(person);
    }

    // 显示测评结结果
    public void display(Action action) {
        for (Person person : personList) {
            person.accept(action);
        }
    }
}
