package models.factory.factoryMethod;

public class BJCheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("北京cheese pizza.");
    }
}
