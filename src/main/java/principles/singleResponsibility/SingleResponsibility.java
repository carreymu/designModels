package principles.singleResponsibility;

public class SingleResponsibility {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("汽车");
        vehicle.runAir("飞机");
        vehicle.runWater("轮船");
    }
}

class Vehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " 在公路上运行...");
    }

    public void runAir(String vehicle) {
        System.out.println(vehicle + " 在天空运行...");
    }

    public void runWater(String vehicle) {
        System.out.println(vehicle + " 在水里运行...");
    }
}