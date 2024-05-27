package designpattern.behavior.commandpattern;

import designpattern.behavior.commandpattern.command.WriteCommand;
import designpattern.behavior.commandpattern.invoker.CommandManager;
import designpattern.behavior.commandpattern.receiver.TextEditor;

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
