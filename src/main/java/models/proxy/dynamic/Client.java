package models.proxy.dynamic;

public class Client {
    public static void main(String[] args) {
        ITeacherDao teacherDao = new TeacherDao();
        ITeacherDao proxyInstance = (ITeacherDao) new ProxyFactory(teacherDao).getProxyInstance();

        // class com.sun.proxy.$Proxy0 内存中动态生成了代理对象
        System.out.println("proxyInstance="+proxyInstance.getClass());
        proxyInstance.teach("English");
    }
}
