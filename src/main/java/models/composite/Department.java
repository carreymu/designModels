package models.composite;

public class Department extends OrganizationComponent{
    public Department(String name, String des) {
        super(name, des);
    }
    // 叶子节点无需管理其他节点

    @Override
    protected void print() {
        System.out.println(getName());
    }
}
