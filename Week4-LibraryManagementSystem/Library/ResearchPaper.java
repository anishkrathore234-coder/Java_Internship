package library;

public class ResearchPaper extends LibraryItem {
    public String field;

    public ResearchPaper(String id, String title, String author, String field) {
        super(id, title, author);
        this.field = field;
    }

    @Override
    public void displayInfo() {
        System.out.println("Research Paper ID: " + id + ", Title: " + title + ", Author: " + author + ", Field: " + field);
    }
}