package models.observer;

public class CurrentConditions implements IObserver{
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
        System.out.println("=====Today's temperature is " + temp + "====");
        System.out.println("=====Today's pressure is " + pressure + "====");
        System.out.println("=====Today's humidity is " + humidity + "====");
    }
}
