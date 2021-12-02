package models.state;

public class Client {
    public static void main(String[] args) {
        RaffleActivity raffleActivity = new RaffleActivity(1);
        for (int i = 0; i < 20; i++) {
            System.out.println("--------第" + i + "次抽奖------");
            // 扣积分
            raffleActivity.deductMoney();
            // 抽奖
            raffleActivity.raffle();
        }
    }
}
