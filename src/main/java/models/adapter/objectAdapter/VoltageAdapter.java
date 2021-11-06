package models.adapter.objectAdapter;

public class VoltageAdapter implements IVoltage5V {
    // 关联关系 - 聚合
    private Voltage220V voltage220V;

    // 通过构造器，传入一个Voltage220v实例
    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    public int output5v() {
        if(null != voltage220V) {
            System.out.println("适配成功>>");
            int srcV = voltage220V.output220v();
            int dstV = srcV / 44;
            return dstV;
        }
        System.out.println("适配失败>>");
        return 0;
    }
}