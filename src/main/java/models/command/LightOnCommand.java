package models.command;

// 将接收者对象和一个动作绑定，调用接受者相应的操作，实现exec
public class LightOnCommand implements ICommand {
    // 聚合Receiver
    LightReceiver light;

    public LightOnCommand(LightReceiver light) {
        this.light = light;
    }


    @Override
    public void exec() {
        // 调用接收者方法
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
