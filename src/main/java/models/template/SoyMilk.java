package models.template;

public abstract class SoyMilk {
    // 模版方法 可以做成final,不让子类覆盖
    final void make() {
        select();
        if (customerCondiments()) {
            addCondiments();
        }
        soak();
        beat();
    }

    // 选材
    void select() {
        System.out.println("选材");
    }

    // 添加不同配料,抽象方法,子类具体实现
    abstract void addCondiments();

    void soak() {
        System.out.println("浸泡");
    }

    void beat() {
        System.out.println("豆子粉身碎骨");
    }

    // 钩子方法,决定某个步骤是否执行
    boolean customerCondiments() {
        return true;
    }
}
