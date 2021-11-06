package models.adapter.classAdapter;

public class VoltageAdapter extends Voltage220V implements IVoltage5V {
    @Override
    public int output5v() {
        int srcV = output220v();
        int dstV = srcV / 44;
        return dstV;
    }
}