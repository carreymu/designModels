package models.observer;

import java.util.ArrayList;

public class WeatherData implements ISubject {
    private float temp;
    private float pressure;
    private float humidity;
    // 观察者集合
    private ArrayList<IObserver> observers;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    public void setData(float temp, float pressure, float humidity) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        dataChange();
    }

    private void dataChange() {
        notifyObservers();
    }

    @Override
    public void registerObserver(IObserver o) {
        System.out.println("---新增" + o.getClass().getName());
        this.observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        if (this.observers.contains(o)) {
            System.out.println("----移除" + o.getClass().getName());
            this.observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(this.temp, this.pressure, this.humidity);
        }
    }
}
