package models.mediator;

import java.util.HashMap;

public class ConcreteMediator extends Mediator {
    // 放入所有的同事对象
    private HashMap<String, Colleague> colleagueHashMap;
    private HashMap<String, String> interMap;

    public ConcreteMediator() {
        colleagueHashMap = new HashMap<>();
        interMap = new HashMap<>();
    }

    @Override
    void Register(String colleagueName, Colleague colleague) {
        colleagueHashMap.put(colleagueName, colleague);
        if (colleague instanceof Alarm) {
            interMap.put("Alarm", colleagueName);
        } else if (colleague instanceof CoffeeMachine) {
            interMap.put("CoffeeMachine", colleagueName);
        } else if(colleague instanceof TV){
            interMap.put("TV", colleagueName);
        }
    }

    @Override
    void getMessage(int stateChange, String colleague) {
        if (colleagueHashMap.get(colleague) instanceof Alarm) {
            if (stateChange == 0) {
                ((CoffeeMachine) (colleagueHashMap.get(interMap.get("CoffeeMachine")))).startCoffeeMachine();
                ((TV) (colleagueHashMap.get(interMap.get("TV")))).startTV();
            } else if (stateChange == 1) {
                ((TV) (colleagueHashMap.get(interMap.get("TV")))).stopTV();
            }
        } else if (colleagueHashMap.get(colleague) instanceof CoffeeMachine) {
            ((TV) (colleagueHashMap.get(interMap.get("TV")))).startTV();
        } // 还可以定义其他连续动作
    }

    @Override
    void sendMessage() {

    }
}
