package models.adapter.springMVCAdapter;

public interface HandlerAdapter {
    boolean supports(Object handler);

    void handler(Object handler);
}

// 多种适配器类
class SimpleHandlerAdapter implements HandlerAdapter {
    @Override
    public void handler(Object handler) {
        ((SimpleController) handler).doSimpleHandler();
    }

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof SimpleController);
    }
}

class AnnotationHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof AnnotationController);
    }

    @Override
    public void handler(Object handler) {
        ((AnnotationController) handler).doAnnotationController();
    }
}

class HttpHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof HttpController);
    }

    @Override
    public void handler(Object handler) {
        ((HttpController) handler).doHttpHandler();
    }
}

