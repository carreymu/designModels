package models.command;

public class Client {
    public static void main(String[] args) {
        // 创建接收者
        LightReceiver receiver = new LightReceiver();

        // 创建电灯相关开关命令
        LightOnCommand lightOnCommand = new LightOnCommand(receiver);
        LightOffCommand lightOffCommand = new LightOffCommand(receiver);

        // 来一个遥控器
        RemoteController remoteController = new RemoteController(5);
        // 设置遥控器命令
        int no = 0;
        remoteController.setCommand(no, lightOnCommand, lightOffCommand);
        System.out.println("--------启动");
        remoteController.onButtonPush(no);
        System.out.println("--------关闭");
        remoteController.offButtonPush(no);
        System.out.println("---------撤销");
        remoteController.undoButtonPush();
    }
}
