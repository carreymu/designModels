package principles.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * 迪米特法则 - 最少知道原则
 * 核心：降低类之间对耦合，并非完全要求没有依赖关系
 * 直接朋友： 每个对象都会与其他对象有耦合关系，只要两个对象直接有耦合关系，我们就说此俩对象之间说朋友关系。
 * 耦合方式有很多种，依赖，关联，组合，聚合等。其中，我们称出现成员变量，方法参数，方法返回值中的类为直接朋
 * 友，而出现在局部变量中的类不是直接朋友。也就是说，陌生的类最好不要以局部变量形式出现在类的内部。
 */
public class Demeter {
    public static void main(String[] args) {
        SchoolManager schoolManager = new SchoolManager();
        schoolManager.printAllEmployee(new CollegeManager());
    }
}

class CollegeManager {
    // 返回学院所有员工
    public List<CollegeEmployee> getAllEmployee() {
        List<CollegeEmployee> collegeEmployeeArrayList = new ArrayList<CollegeEmployee>();
        for (int i = 0; i < 10; i++) {
            CollegeEmployee collegeEmployee = new CollegeEmployee();
            collegeEmployee.setId("学院员工ID= " + i);
            collegeEmployeeArrayList.add(collegeEmployee);
        }
        return collegeEmployeeArrayList;
    }

    public void printEmployee() {
        List<CollegeEmployee> allEmployee = getAllEmployee();
        System.out.println("-----学院员工");
        for (CollegeEmployee employee : allEmployee) {
            System.out.println(employee.getId());
        }
    }
}

class SchoolManager {
    public List<Employee> getAllEmployee() {
        List<Employee> employeesList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Employee employee = new Employee();
            employee.setId("学校总部员工id="+ i);
            employeesList.add(employee);
        }
        return employeesList;
    }

    void printAllEmployee(CollegeManager sub){
        // CollegeManager陌生类不要出现在SchoolManager中，减少耦合
        sub.printEmployee();
        List<Employee> allEmployee = getAllEmployee();
        System.out.println("------学校总部员工");
        for (Employee e : allEmployee) {
            System.out.println(e.getId());
        }
    }
}

class CollegeEmployee {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class Employee {
private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}