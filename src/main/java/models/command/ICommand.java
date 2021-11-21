package models.command;

// 命令者
public interface ICommand {
    void exec();
    void undo();
}
