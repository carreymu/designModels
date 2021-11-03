package models.prototype;

import java.io.*;

public class DeepProtoType implements Serializable, Cloneable {
    public String name; // String 属性
    public DeepCloneableTarget deepCloneableTarget;// 引用类型

    public DeepProtoType() {
        super();
    }

    // 方式1：使用clone方法
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object deep = null;
        // 这里完成对基本数据类型（属性）和String对克隆
        deep = super.clone();

        // 对引用类型对属性单独处理
        DeepProtoType deepProtoType = (DeepProtoType) deep;
        deepProtoType.deepCloneableTarget = (DeepCloneableTarget) deepCloneableTarget.clone();

        return deepProtoType;
    }

    // 方式2：使用对象对序列化实现(推荐)
    protected Object deepClone() {
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try{
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);// 当前这个对象以对象流方式输出

            // 反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            DeepProtoType cpObj = (DeepProtoType) ois.readObject();
            return cpObj;
        } catch (Exception e){
            e.printStackTrace();;
            return null;
        } finally {
            // 关闭流
            try {
                bis.close();
                ois.close();
                bos.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
