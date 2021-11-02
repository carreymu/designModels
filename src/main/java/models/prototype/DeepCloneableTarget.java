package models.prototype;

import java.io.Serializable;

public class DeepCloneableTarget implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private String cloneName;
    private String cloneClass;

    public DeepCloneableTarget(String cloneName, String cloneClass) {
        this.cloneName = cloneName;
        this.cloneClass = cloneClass;
    }

    // String的hashCode不变
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
