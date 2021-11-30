package models.state;

public interface IState {
    void deduceMoney();
    boolean raffle();
    void dispensePrize();
}
