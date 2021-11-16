package models.flyweight;

import java.util.HashMap;

// 网站工厂类,根据需求返回一个网站
public class WebsiteFactory {
    // 集合，充当池的作用
    private HashMap<String, ConcreteWebsite> pool = new HashMap<>();

    // 根据网站类型，返回一个网站，若没则创建一个网站，并发池中返回
    public Website getWebsiteCategory(String type){
        if(!pool.containsKey(type)){
            pool.put(type, new ConcreteWebsite(type));
        }
        return (Website)pool.get(type);
    }

    // 获取网站分类总数
    public int getWebsiteCount(){
        return pool.size();
    }
}
