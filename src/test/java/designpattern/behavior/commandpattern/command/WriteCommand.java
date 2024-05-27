package designpattern.behavior.commandpattern.command;

import designpattern.behavior.commandpattern.receiver.TextEditor;

public class WriteCommand implements Command{
    private TextEditor textEditor;
    private String textToAdd;

    public WriteCommand(TextEditor textEditor, String textToAdd) {
        this.textEditor = textEditor;
        this.textToAdd = textToAdd;
    }


    @Override
    public void execute() {
        textEditor.write(textToAdd);
    }

    @Override
    public void undo() {
        textEditor.erase(textToAdd.length());
    }
}
