package models.iterator;

import java.util.Iterator;

public class ComputerCollege implements ICollege {
    Department[] departments;
    int numOfDepartment = 0; // 保存当前数组对象个数

    public ComputerCollege(int size) {
        this.departments = new Department[size];
        addDepartment("A专业", "A");
        addDepartment("B专业", "B");
        addDepartment("C专业", "C");
    }

    @Override
    public String getName() {
        return "计算机专业";
    }

    @Override
    public void addDepartment(String name, String desc) {
        Department department = new Department(name, desc);
        departments[numOfDepartment] = department;
        numOfDepartment++;
    }

    @Override
    public Iterator createIterator() {
        return new ComputerCollegeIterator(departments);
    }
}
