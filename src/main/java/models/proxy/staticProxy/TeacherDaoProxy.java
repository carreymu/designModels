package models.proxy.staticProxy;

// 代理对象，静态代理
public class TeacherDaoProxy implements ITeacherDao{
    private ITeacherDao target; // 目标对象，通过接口实现聚合

    public TeacherDaoProxy(ITeacherDao target) {
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("开始代理。。");
        System.out.println("代理之前干了其他事情》》》");
        target.teach();
        System.out.println("代理结束");
    }
}
