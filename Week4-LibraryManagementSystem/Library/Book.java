package library;

public class Book extends LibraryItem {
    public String genre;

    public Book(String id, String title, String author, String genre) {
        super(id, title, author);
        this.genre = genre;
    }

    @Override
    public void displayInfo() {
        System.out.println("Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Genre: " + genre);
    }
}