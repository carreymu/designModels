package models.observer;

// 观察者接口，由观察者实现
public interface IObserver {
    void update(float temp, float pressure, float humidity);
}
