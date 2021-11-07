package models.adapter.springMVCAdapter;

// 多种Controller实现
public interface Controller {
}

class HttpController implements Controller {
    public void doHttpHandler() {
        System.out.println("http>>>");
    }
}

class SimpleController implements Controller {
    public void doSimpleHandler() {
        System.out.println("simple>>>");
    }
}

class AnnotationController implements Controller {
    public void doAnnotationController() {
        System.out.println("annotation>>>");
    }
}