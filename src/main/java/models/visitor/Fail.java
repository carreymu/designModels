package models.visitor;

public class Fail extends Action{
    @Override
    public void getManResult(Man man) {
        System.out.println("You are not masculine..");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("You are not feminine..");
    }
}
