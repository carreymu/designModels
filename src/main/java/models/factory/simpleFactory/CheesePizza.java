package models.factory.simpleFactory;

public class CheesePizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("CheesePizza preparation");
    }
}
