package models.adapter.springMVCAdapter;

import java.util.ArrayList;
import java.util.List;

public class DispatchServlet {
    public static List<HandlerAdapter> handlerAdapterList = new ArrayList<>();

    public DispatchServlet() {
        handlerAdapterList.add(new AnnotationHandlerAdapter());
        handlerAdapterList.add(new HttpHandlerAdapter());
        handlerAdapterList.add(new SimpleHandlerAdapter());
    }

    public void doDispatch() {
        // 此处模拟SpringMVC从request取handler的对象,适配器可以获取到希望到Controller
        //HttpController controller = new HttpController();
        SimpleController controller = new SimpleController();
        // 得到对应适配器
        HandlerAdapter adapter = getHandler(controller);
        // 通过适配器执行对应到Controller方法
        adapter.handler(controller);
    }

    private HandlerAdapter getHandler(Controller controller) {
        for (HandlerAdapter adapter : this.handlerAdapterList) {
            if (adapter.supports(controller)) {
                return adapter;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new DispatchServlet().doDispatch();
    }
}
