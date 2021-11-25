package models.observer;

public class Client {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        // 创建观察者
        CurrentConditions currentConditions = new CurrentConditions();
        // 注册到weather
        weatherData.registerObserver(currentConditions);

        Netease netease = new Netease();
        weatherData.registerObserver(netease);
        System.out.println("========通知注册的观察者=====");
        weatherData.removeObserver(netease);
        weatherData.setData(1f,2f,3f);
    }
}
