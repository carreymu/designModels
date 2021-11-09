package models.composite;

/**
 * 当我们要处理的对象可以生成一棵树形结构,而我们要对树上的节点和叶子进行操作时,他能够提供
 * 一致的方式，而无需考虑它是节点还是叶子
 */
public abstract class OrganizationComponent {
    private String name;
    private String des; // 说明

    public OrganizationComponent(String name, String des) {
        this.name = name;
        this.des = des;
    }

    protected void add(OrganizationComponent organizationComponent) {
        throw new UnsupportedOperationException();
    }

    protected void remove(OrganizationComponent organizationComponent) {
        throw new UnsupportedOperationException();
    }

    // 所有子类都需要实现print方法
    protected abstract void print();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
