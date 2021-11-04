package models.builder;

public class Client {
    public static void main(String[] args) {
        // 盖普通房子
        CommonHouse commonHouse = new CommonHouse();

        // 准备创建房子打Director
        HouseDirector houseDirector = new HouseDirector(commonHouse);

        // 完成盖房流程，返回房子
        houseDirector.constructHouse();
        System.out.println("普通房子盖好>>>>");
        System.out.println("======");

        // 盖高楼
        HighBuilding highBuilding = new HighBuilding();
        // 重置建造者
        houseDirector.setHouseBuilder(highBuilding);
        // 完成高楼建设，返回房子
        houseDirector.constructHouse();
        System.out.println("万丈高楼拔地起>>>>");
    }
 }
