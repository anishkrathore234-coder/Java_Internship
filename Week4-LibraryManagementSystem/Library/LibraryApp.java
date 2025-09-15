package library;

import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Library library = new Library();
        library.loadData(); // Load data at startup
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add new item");
            System.out.println("2. Register new member");
            System.out.println("3. Borrow an item");
            System.out.println("4. Return an item");
            System.out.println("5. List all available items");
            System.out.println("6. List borrowed items of a member");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                if (choice == 1) {
                    System.out.print("Type (book/magazine/research): ");
                    String type = sc.nextLine();
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();

                    if (type.equalsIgnoreCase("book")) {
                        System.out.print("Genre: ");
                        String genre = sc.nextLine();
                        library.addItem(new Book(id, title, author, genre));
                    } else if (type.equalsIgnoreCase("magazine")) {
                        System.out.print("Issue Number: ");
                        String issue = sc.nextLine();
                        library.addItem(new Magazine(id, title, author, issue));
                    } else if (type.equalsIgnoreCase("research")) {
                        System.out.print("Field: ");
                        String field = sc.nextLine();
                        library.addItem(new ResearchPaper(id, title, author, field));
                    }
                } else if (choice == 2) {
                    System.out.print("Member ID: ");
                    String memberId = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    library.registerMember(new Member(memberId, name));
                } else if (choice == 3) {
                    System.out.print("Member ID: ");
                    String memberId = sc.nextLine();
                    System.out.print("Item ID: ");
                    String itemId = sc.nextLine();
                    library.borrowItem(memberId, itemId);
                } else if (choice == 4) {
                    System.out.print("Member ID: ");
                    String memberId = sc.nextLine();
                    System.out.print("Item ID: ");
                    String itemId = sc.nextLine();
                    library.returnItem(memberId, itemId);
                } else if (choice == 5) {
                    library.listAvailableItems();
                } else if (choice == 6) {
                    System.out.print("Member ID: ");
                    String memberId = sc.nextLine();
                    library.listBorrowedItems(memberId);
                } else if (choice == 7) {
                    library.saveData(); // Save data on exit
                    System.out.println("Exiting...");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
    }
}