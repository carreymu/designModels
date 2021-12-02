package models.state;

import jdk.nashorn.internal.ir.CallNode;

public class RaffleActivity {
    IState state = null;
    int count = 0;
    IState noRaffleState = new NoRaffleState(this);
    IState canRaffleState = new CanRaffleState(this);

    public IState getCanRaffleState() {
        return canRaffleState;
    }

    public void setCanRaffleState(IState canRaffleState) {
        this.canRaffleState = canRaffleState;
    }

    public IState getDispenseState() {
        return dispenseState;
    }

    public void setDispenseState(IState dispenseState) {
        this.dispenseState = dispenseState;
    }

    public IState getDispenseOutState() {
        return dispenseOutState;
    }

    public void setDispenseOutState(IState dispenseOutState) {
        this.dispenseOutState = dispenseOutState;
    }

    IState dispenseState = new DispenseState(this);
    IState dispenseOutState = new DispenseOutState(this);


    public RaffleActivity(int count) {
        this.state = getNoRaffleState();
        this.count = count;
    }

    public void deductMoney() {
        state.deduceMoney();
    }

    public void raffle() {
        // 抽奖成功
        if (state.raffle()) {
            state.dispensePrize();
        }
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    // 领一次奖品，count--
    public int getCount() {
        int curCount = count;
        count--;
        return curCount;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public IState getNoRaffleState() {
        return this.noRaffleState;
    }
}
