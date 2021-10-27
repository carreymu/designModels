package models.factory.abstractFactory.piza;

public class BJPepperPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("北京pepper pizza");
    }
}
