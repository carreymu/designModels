package models.state;

public class DispenseState implements IState {
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
        if (raffleActivity.getCount() > 0) {
            System.out.println("**恭喜中奖");
            // 状态-不能抽奖
            raffleActivity.setState(raffleActivity.getNoRaffleState());
        } else {
            System.out.println("**遗憾，奖品发完。");
            // 状态-发奖完毕
            raffleActivity.setState(raffleActivity.getDispenseState());
            System.out.println("**完犊子吧。");
        }
    }
}
