package models.template;

public class PureSoyMilk extends SoyMilk{
    @Override
    void addCondiments() {

    }
    @Override
    boolean customerCondiments() {
        return false;
    }
}
