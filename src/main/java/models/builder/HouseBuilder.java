package models.builder;

// 抽象打建造者
public abstract class HouseBuilder {
    // 打地基
    protected  House house = new House();

    // 建造流程
    public abstract void buildBase();
    public abstract void buildWall();
    public abstract void buildroof();

    // 建造成功后返回
    public House buildHouse(){
        return house;
    }
}
