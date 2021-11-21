package models.command;

public class RemoteController {
    // 开 按钮命令数组
    ICommand[] onCommands;
    ICommand[] offCommands;

    // 执行命令撤销
    ICommand undoCommand;

    public RemoteController(int commandSize) {
        this.onCommands = new ICommand[commandSize];
        this.offCommands = new ICommand[commandSize];
        for (int i = 0; i < commandSize; i++) {
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
    }

    // 输入第几行命令
    public void setCommand(int no, ICommand onCommand, ICommand offCommand) {
        onCommands[no] = onCommand;
        offCommands[no] = offCommand;
    }

    // 按下按钮
    public void onButtonPush(int no) {
        // 找到按下的开按钮，执行方法
        onCommands[no].exec();
        // 记录哪个按钮被操作
        undoCommand = onCommands[no];
    }

    // 按下按钮
    public void offButtonPush(int no) {
        // 找到按下的关按钮，执行方法
        offCommands[no].exec();
        // 记录哪个按钮被操作
        undoCommand = offCommands[no];
    }

    // 按下撤销按钮
    public void undoButtonPush() {
        undoCommand.undo();
    }
}
