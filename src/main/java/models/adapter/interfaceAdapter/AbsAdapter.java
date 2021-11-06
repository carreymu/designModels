package models.adapter.interfaceAdapter;

public abstract class AbsAdapter implements InterfaceOrg {
    @Override
    public void m1() {

    }

    @Override
    public void m2() {

    }

    // 可实现也可以不实现，比如本例实现类m3,m1和m2我并不关系
    @Override
    public void m3() {
        System.out.println("我只关系m3,m1和m2靠边站>>>");
    }
}
