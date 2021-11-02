package models.prototype;

public class Client {
    public static void main(String[] args) {
        System.out.println("原型模式");
        Sheep sheep = new Sheep("张三", 1, "RED");
        sheep.friend = new Sheep("king", 2, "green");
        Sheep sheep1 = (Sheep) sheep.clone();

        System.out.println(sheep.toString());
        System.out.println(sheep1.toString());

        System.out.println(sheep == sheep1);
        System.out.println("sheep.friend.hashCode: " + sheep.friend.hashCode());
        System.out.println("sheep1.friend.hashCode: " + sheep1.friend.hashCode());
    }
}
