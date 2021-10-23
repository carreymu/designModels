package principles.segregation;

public class segregation {
    public static void main(String[] args) {
        A a = new A();
        //A通过接口以依赖B类
        a.depend1(new B());
        a.depend2(new B());
        a.depend3(new B());

        System.out.println("-------");
        C c = new C();
        //C通过接口依赖D类
        c.depend1(new D());
        c.depend4(new D());
        c.depend5(new D());
    }
}

/**
 * interface region
 */
interface Interface1 {
    void operation1();
}

interface Interface2 {
    void operation2();

    void operation3();
}

interface Interface3 {
    void operation4();

    void operation5();
}

/**
 * class-implement region
 */
class A {
    //A通过接口Interface1 依赖(使用)B类,但是只用到1，2，3方法
    public void depend1(Interface1 interface1) {
        interface1.operation1();
    }

    public void depend2(Interface2 interface2) {
        interface2.operation2();
    }

    public void depend3(Interface2 interface2) {
        interface2.operation3();
    }
}

class C {
    //C通过接口Interface1,Interface3依赖(使用)D类，但是只用1，4，5方法
    public void depend1(Interface1 interface1) {
        interface1.operation1();
    }

    public void depend4(Interface3 interface3) {
        interface3.operation4();
    }

    public void depend5(Interface3 interface3) {
        interface3.operation5();
    }
}

class B implements Interface1, Interface2 {

    @Override
    public void operation1() {
        System.out.println("B 实现了 operation1");
    }

    @Override
    public void operation2() {
        System.out.println("B 实现了 operation2");
    }

    @Override
    public void operation3() {
        System.out.println("B 实现了 operation3");
    }
}

class D implements Interface1, Interface3 {

    @Override
    public void operation1() {
        System.out.println("D 实现了 operation1");
    }

    @Override
    public void operation4() {
        System.out.println("D 实现了 operation4");
    }

    @Override
    public void operation5() {
        System.out.println("D 实现了 operation5");
    }
}
