package models.composite;

public class Client {
    public static void main(String[] args) {
        // 从大到小创建对象
        OrganizationComponent university = new University("上海大学", "还行");

        // 创建学院
        OrganizationComponent computerCollege = new College("计算机学院", "蓝领One");
        OrganizationComponent infoCollege = new College("信息工程学院", "蓝领Two");

        // 创建学院下的专业
        computerCollege.add(new Department("软件工程", "码农"));
        computerCollege.add(new Department("网络工程", "网农"));

        infoCollege.add(new Department("信息工程", "预备役码农"));
        infoCollege.add(new Department("通信工程", "预备役码农"));

        university.add(computerCollege);
        university.add(infoCollege);

        university.print();
    }
}
