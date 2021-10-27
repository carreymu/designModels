package models.factory.abstractFactory.order;

import models.factory.abstractFactory.piza.Pizza;

// 抽象工厂接口
public interface AbsFactory {
    // 让工厂子类实现具体操作
    Pizza createPizza(String orderType);
}
