package models.bridge;

/**
 * 对于不希望使用继承或因多层次继承导致系统类个数急剧增加多系统，比较适用。
 * 应用场景：
 * 1. JDBC驱动
 *  驱动类型：(DriverManager) MySQL驱动，Oracle驱动...
 *  连接分类：各种Connection; 如java.sql,com.mysql.jdbc,org.springframework.jdbc.datasource
 * 2. 银行转账系统
 *  转账分类：网上转账，柜台转账，ATM转账
 *  转账用户类型：普通用户，银卡用户，金卡用户...
 * 3. 消息管理
 *  消息类型：即时消息，延迟消息
 *  消息分类：手机短信，邮件消息，QQ消息
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("-----折叠手机>>>>");
        Phone foldedPhone = new FoldedPhone(new XiaoMi());
        foldedPhone.call();
        foldedPhone.open();
        foldedPhone.close();

        System.out.println("=======");
        foldedPhone = new FoldedPhone(new Vivo());
        foldedPhone.open();
        foldedPhone.call();
        foldedPhone.close();

        System.out.println("-----直板手机>>>");
        Phone uprightPhone = new UprightPhone(new XiaoMi());
        uprightPhone.open();
        uprightPhone.call();
        uprightPhone.close();

        System.out.println("=====");
        uprightPhone = new UprightPhone(new Vivo());
        uprightPhone.open();
        uprightPhone.close();
        uprightPhone.call();
    }
}
