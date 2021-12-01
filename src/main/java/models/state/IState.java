package models.state;

public interface IState {
    // 扣积分
    void deduceMoney();

    // 是否抽中奖品
    boolean raffle();

    // 发奖
    void dispensePrize();
}
