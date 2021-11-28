package models.memento;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    // list中会有很多备忘录对象, memento可以是复杂的hashmap
    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento memento) {
        mementoList.add(memento);
    }

    // 获取第n个Originator的备忘录对象(即保存状态)
    public Memento get(int index) {
        return mementoList.get(index);
    }
}
