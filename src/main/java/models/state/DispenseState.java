package models.state;

public class DispenseState implements IState{
    RaffleActivity raffleActivity;

    public DispenseState(RaffleActivity raffleActivity) {
        this.raffleActivity = raffleActivity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("**不能扣积分。");
    }

    @Override
    public boolean raffle() {
        System.out.println("**不能抽奖。");
        return false;
    }

    @Override
    public void dispensePrize() {

    }
}
