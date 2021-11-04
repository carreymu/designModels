package models.builder;

public class HighBuilding extends HouseBuilder {
    @Override
    public void buildBase() {
        System.out.println("高楼30米地基");
    }

    @Override
    public void buildWall() {
        System.out.println("高楼砌墙40cm");
    }

    @Override
    public void buildroof() {
        System.out.println("高楼建玻璃屋顶");
    }
}
