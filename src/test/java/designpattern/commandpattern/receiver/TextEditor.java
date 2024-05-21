package designpattern.commandpattern.receiver;

public class TextEditor {
    private StringBuilder content = new StringBuilder();
    public void write(String textToAdd){
        content.append(textToAdd);
    }
    public void erase(int length){
        if(content.length() >= length){
            content.delete(content.length() - length, content.length());
        }
    }
    public String getContent(){
        return content.toString();
    }
}
