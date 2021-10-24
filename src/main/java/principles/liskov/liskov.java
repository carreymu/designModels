package principles.liskov;

import jdk.nashorn.internal.ir.CallNode;

public class liskov {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(" 5-3=" + a.func1(5, 3));
        System.out.println(" 3-7=" + a.func1(3, 7));

        System.out.println("--------");

        B b = new B();
        // 避免无意替换
        System.out.println(" 5-3=" + b.func1(5, 3));
        System.out.println(" 5-4+1=" + b.func2(5, 4));
        // 更加明确调用
        System.out.println(" 8-6=" + b.func3(8, 6));
    }
}

class Base {
    // 把更加基础到方法和成员写到Base类
    // 尽量不要改写父类方法
    // 继承实际让两个类耦合性增强类，适当情况下，可以通过聚合，组合，依赖解决问题。
}

class A extends Base {
    public int func1(int num1, int num2) {
        return num1 - num2;
    }
}


class B extends Base {
    public int func1(int a, int b) {
        return a - b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 1;
    }

    // 调用A内到方法
    private A a = new A();

    public int func3(int a, int b) {
        return this.a.func1(a, b);
    }
}
