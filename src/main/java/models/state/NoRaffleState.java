package models.state;

/**
 * 不能抽奖状态
 */
public class NoRaffleState implements IState {
    RaffleActivity raffleActivity;

    public NoRaffleState(RaffleActivity raffleActivity) {
        this.raffleActivity = raffleActivity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("===扣积分，可以抽奖。");
        raffleActivity.setState(raffleActivity.getCanRaffleState());
    }

    @Override
    public boolean raffle() {
        System.out.println("===扣类积分才可以抽奖啊。");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("===不能发奖。");
    }
}
