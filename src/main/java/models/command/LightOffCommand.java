package models.command;

public class LightOffCommand implements ICommand{
    // 聚合Receiver
    LightReceiver light;

    public LightOffCommand(LightReceiver light) {
        this.light = light;
    }


    @Override
    public void exec() {
        // 调用接收者方法
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
