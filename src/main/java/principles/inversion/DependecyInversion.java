package principles.inversion;

public class DependecyInversion {
    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
        System.out.println("------");
        person.receive(new WeChat());
    }
}

class Person {
    // 参数使用接口，接口可以有多个扩展，接口作为其他实现方法的缓存层
    public void receive(IReceiver receiver) {
        System.out.println(receiver.getInfo());
    }
}

interface IReceiver {
    String getInfo();
}

class Email implements IReceiver {
    @Override
    public String getInfo() {
        return "Email: hello,mm.";
    }
}

class WeChat implements IReceiver {
    @Override
    public String getInfo() {
        return "Email: hello,wechat.";
    }
}