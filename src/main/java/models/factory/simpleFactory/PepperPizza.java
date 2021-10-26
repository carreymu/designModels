package models.factory.simpleFactory;

public class PepperPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("PepperPizza preparation.");
    }
}
