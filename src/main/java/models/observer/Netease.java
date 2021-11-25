package models.observer;

public class Netease implements IObserver{
    private float temp;
    private float pressure;
    private float humidity;

    @Override
    public void update(float temp, float pressure, float humidity) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    private void display() {
        System.out.println("-----Netease's temperature is " + temp + "====");
        System.out.println("-----Netease's temperature is " + pressure + "====");
        System.out.println("-----Netease's temperature is " + humidity + "====");
    }
}
