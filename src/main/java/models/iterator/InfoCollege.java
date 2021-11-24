package models.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InfoCollege implements ICollege {
    List<Department> departments;

    public InfoCollege() {
        this.departments = new ArrayList<>();
        addDepartment("C专业", "CCC");
        addDepartment("D专业", "DDD");
        addDepartment("E专业", "EEE");
    }

    @Override
    public String getName() {
        return "信息工程学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        departments.add(new Department(name, desc));
    }

    @Override
    public Iterator createIterator() {
        return new InfoCollegeIterator(departments);
    }
}
