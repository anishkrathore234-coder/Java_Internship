// library/LibraryItem.java
package library;

public class LibraryItem {
    protected String id;
    protected String title;
    protected String author;

    public LibraryItem(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public void displayInfo() {
        System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author);
    }
}
