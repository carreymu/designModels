package models.prototype;

public class DeepClient {
    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("=====方式1");
        DeepProtoType deepProtoType = new DeepProtoType();
        deepProtoType.name = "张三";
        deepProtoType.deepCloneableTarget = new DeepCloneableTarget("哎呦", "矮油");

        DeepProtoType clone = (DeepProtoType) deepProtoType.clone();
        System.out.println("deepProtoType.name=" + deepProtoType.name + ",deepProtoType.deepCloneableTarget=" + deepProtoType.deepCloneableTarget.hashCode());
        System.out.println("clone.name=" + clone.name + ",clone.deepCloneableTarget=" + clone.deepCloneableTarget.hashCode());

        System.out.println("=====方式2");
        DeepProtoType clone2 = (DeepProtoType) deepProtoType.deepClone();
        System.out.println("deepProtoType.name=" + deepProtoType.name + ",deepProtoType.deepCloneableTarget=" + deepProtoType.deepCloneableTarget.hashCode());
        System.out.println("clone2.name=" + clone2.name + ",clone2.deepCloneableTarget=" + clone2.deepCloneableTarget.hashCode());

    }
}
