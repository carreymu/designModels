package models.flyweight;

import com.sun.javafx.scene.traversal.WeightedClosestCorner;

public class Client {
    public static void main(String[] args) {

        // 创建一个工厂类
        WebsiteFactory factory = new WebsiteFactory();

        // 新闻发布网站
        Website news = factory.getWebsiteCategory("News");
        news.use();

        // 博客发布网站
        Website weibo = factory.getWebsiteCategory("weibo");
        weibo.use();

        Website wb = factory.getWebsiteCategory("weibo");
        wb.use();

        System.out.println("网站分类数："+factory.getWebsiteCount());
    }
}
