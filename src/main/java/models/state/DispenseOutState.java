package models.state;

/**
 * 发完奖品
 */
public class DispenseOutState implements IState{
RaffleActivity activity;

    public DispenseOutState(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("##奖已发完");
    }

    @Override
    public boolean raffle() {
        System.out.println("##回去吧，没奖了。");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("##回家啊玩吧，没奖了。");
    }
}
