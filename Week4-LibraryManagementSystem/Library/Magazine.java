package library;

public class Magazine extends LibraryItem {
    public String issueNumber;

    public Magazine(String id, String title, String author, String issueNumber) {
        super(id, title, author);
        this.issueNumber = issueNumber;
    }

    @Override
    public void displayInfo() {
        System.out.println("Magazine ID: " + id + ", Title: " + title + ", Author: " + author + ", Issue: " + issueNumber);
    }
}