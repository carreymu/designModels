package principles.ocp;

// 对扩展开发(对提供方),对修改关闭(对使用方)
public class Ocp {
    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Triangle());
    }
}

// 使用绘制的各种方法
class GraphicEditor {
    public void drawShape(Shape shape) {
        shape.draw();
    }
}

// 基类
abstract class Shape {
    int m_type;

    // 抽象方法由各个继承的类实现，避免修改调用的地方
    abstract void draw();
}

class Rectangle extends Shape {
    Rectangle() {
        super.m_type = 1;
    }

    @Override
    void draw() {
        System.out.println("绘制矩形");
    }
}

class Circle extends Shape {
    Circle() {
        super.m_type = 2;
    }

    @Override
    void draw() {
        System.out.println("绘制圆形");
    }
}

// 新增一个时
class Triangle extends Shape {
    Triangle() {
        super.m_type = 3;
    }

    @Override
    void draw() {
        System.out.println("绘制三角形");
    }
}