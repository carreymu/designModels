package models.iterator;

import java.util.Iterator;
import java.util.List;

public class OutputImpl {
    List<ICollege> collegeList;

    public OutputImpl(List<ICollege> collegeList) {
        this.collegeList = collegeList;
    }

    public void printDepartment(Iterator iterator) {
        while (iterator.hasNext()) {
            Department d = (Department) iterator.next();
            System.out.println(d.getName() + "-" + d.getDesc());
        }
    }

    public void printCollege() {
        Iterator<ICollege> iterator = collegeList.iterator();
        while (iterator.hasNext()) {
            ICollege next = iterator.next();
            System.out.println("==" + next.getName() + "===");
            printDepartment(next.createIterator());
        }
    }
}
