package models.state;

import java.util.Random;

public class CanRaffleState implements IState {
    RaffleActivity raffleActivity;

    public CanRaffleState(RaffleActivity raffleActivity) {
        this.raffleActivity = raffleActivity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("--已扣积分");
    }

    @Override
    public boolean raffle() {
        System.out.println("--正在抽奖，稍等。");
        Random random = new Random();
        if (random.nextInt(10) == 0) {
            raffleActivity.setState(raffleActivity.getDispenseState());
            return true;
        } else {
            System.out.println("--未中。");
            raffleActivity.setState(raffleActivity.getNoRaffleState());
            return false;
        }
    }

    @Override
    public void dispensePrize() {
        System.out.println("--未中奖，不能发奖。");
    }
}
