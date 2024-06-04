package solid.srp.before;

public class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void print(){
        System.out.println("Title: "+ title + ", Author: "+ author);
    }
    public void saveToFile(){
        //.....
    }
}
