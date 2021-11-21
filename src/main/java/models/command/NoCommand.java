package models.command;

// 空命令，用于初始化每个按钮；可以省去空判断
public class NoCommand implements ICommand{
    @Override
    public void exec() {

    }

    @Override
    public void undo() {

    }
}
