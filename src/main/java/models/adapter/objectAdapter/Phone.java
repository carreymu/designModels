package models.adapter.objectAdapter;

public class Phone {
    public void charging(IVoltage5V iVoltage5V){
        if (iVoltage5V.output5v() != 5) {
            System.out.println("电压不符合要求");
        }
        System.out.println("电压5V,吃电中...");
    }
}
