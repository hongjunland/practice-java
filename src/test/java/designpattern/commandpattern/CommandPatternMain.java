package designpattern.commandpattern;

import designpattern.commandpattern.command.WriteCommand;
import designpattern.commandpattern.invoker.CommandManager;
import designpattern.commandpattern.receiver.TextEditor;

public class CommandPatternMain {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        CommandManager commandManager = new CommandManager();

        commandManager.executeCommand(new WriteCommand(textEditor, "Hello, "));
        commandManager.executeCommand(new WriteCommand(textEditor, "world!!"));

        System.out.println("Current text: " + textEditor.getContent());

        commandManager.undo();
        System.out.println("After undo: "+ textEditor.getContent());

        commandManager.redo();
        System.out.println("After redo: "+ textEditor.getContent());
    }
}
