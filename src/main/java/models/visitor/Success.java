package models.visitor;

public class Success extends Action{

    @Override
    public void getManResult(Man man) {
        System.out.println("你很Man,成功。。");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("你很Woman,成功..");
    }
}
