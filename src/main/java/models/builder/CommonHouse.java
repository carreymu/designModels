package models.builder;

public class CommonHouse extends HouseBuilder {
    @Override
    public void buildBase() {
        System.out.println("普通房子打地基2米");
    }

    @Override
    public void buildWall() {
        System.out.println("普通房子砌墙20cm");
    }

    @Override
    public void buildroof() {
        System.out.println("普通房子建屋顶");
    }
}
