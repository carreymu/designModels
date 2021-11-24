package models.iterator;

import java.util.Iterator;

public interface ICollege {
    String getName();

    void addDepartment(String name, String desc);

    // 创建迭代器
    Iterator createIterator();
}
