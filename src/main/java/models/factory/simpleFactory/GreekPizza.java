package models.factory.simpleFactory;

public class GreekPizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("Greek Pizza preparation.");
    }
}
