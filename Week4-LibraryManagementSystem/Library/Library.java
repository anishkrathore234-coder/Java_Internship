package library;

import java.util.*;
import java.io.*;

public class Library {
    private ArrayList<LibraryItem> items;
    private ArrayList<Member> members;

    public Library() {
        items = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public void borrowItem(String memberId, String itemId) throws ItemNotAvailableException, InvalidIdException {
        Member member = findMemberById(memberId);
        LibraryItem item = findItemById(itemId);

        if (member == null) throw new InvalidIdException("Member ID not found.");
        if (item == null) throw new InvalidIdException("Item ID not found.");
        if (member.getBorrowedItems().size() >= 3) throw new ItemNotAvailableException("Max 3 items allowed.");
        if (!items.contains(item)) throw new ItemNotAvailableException("Item not available.");

        member.borrowItem(item);
        items.remove(item);
    }

    public void returnItem(String memberId, String itemId) throws InvalidIdException {
        Member member = findMemberById(memberId);
        LibraryItem item = findItemByIdInBorrowed(member, itemId);

        if (member == null) throw new InvalidIdException("Member ID not found.");
        if (item == null) throw new InvalidIdException("Item ID not found.");

        member.returnItem(item);
        items.add(item);
    }

    public void listAvailableItems() {
        for (LibraryItem item : items) {
            item.displayInfo();
        }
    }

    public void listBorrowedItems(String memberId) throws InvalidIdException {
        Member member = findMemberById(memberId);
        if (member == null) throw new InvalidIdException("Member ID not found.");
        for (LibraryItem item : member.getBorrowedItems()) {
            item.displayInfo();
        }
    }

    private Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    private LibraryItem findItemById(String itemId) {
        for (LibraryItem item : items) {
            if (item.id.equals(itemId)) {
                return item;
            }
        }
        return null;
    }

    private LibraryItem findItemByIdInBorrowed(Member member, String itemId) {
        if (member == null) return null;
        for (LibraryItem item : member.getBorrowedItems()) {
            if (item.id.equals(itemId)) {
                return item;
            }
        }
        return null;
    }

    // File persistence methods
    public void saveData() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("librarydata.txt"));
            // Save items
            for (LibraryItem item : items) {
                if (item instanceof Book) {
                    Book b = (Book) item;
                    pw.println("Book," + b.id + "," + b.title + "," + b.author + "," + b.genre);
                } else if (item instanceof Magazine) {
                    Magazine m = (Magazine) item;
                    pw.println("Magazine," + m.id + "," + m.title + "," + m.author + "," + m.issueNumber);
                } else if (item instanceof ResearchPaper) {
                    ResearchPaper r = (ResearchPaper) item;
                    pw.println("ResearchPaper," + r.id + "," + r.title + "," + r.author + "," + r.field);
                }
            }
            // Save members
            for (Member member : members) {
                pw.print("Member," + member.getMemberId() + "," + member.getName());
                for (LibraryItem item : member.getBorrowedItems()) {
                    pw.print("," + item.id);
                }
                pw.println();
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadData() {
        items.clear();
        members.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader("librarydata.txt"));
            String line;
            Map<String, LibraryItem> itemMap = new HashMap<>();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("Book")) {
                    Book b = new Book(parts[1], parts[2], parts[3], parts[4]);
                    items.add(b);
                    itemMap.put(b.id, b);
                } else if (parts[0].equals("Magazine")) {
                    Magazine m = new Magazine(parts[1], parts[2], parts[3], parts[4]);
                    items.add(m);
                    itemMap.put(m.id, m);
                } else if (parts[0].equals("ResearchPaper")) {
                    ResearchPaper r = new ResearchPaper(parts[1], parts[2], parts[3], parts[4]);
                    items.add(r);
                    itemMap.put(r.id, r);
                } else if (parts[0].equals("Member")) {
                    Member member = new Member(parts[1], parts[2]);
                    for (int i = 3; i < parts.length; i++) {
                        LibraryItem borrowed = itemMap.get(parts[i]);
                        if (borrowed != null) {
                            member.borrowItem(borrowed);
                        }
                    }
                    members.add(member);
                }
            }
            br.close();
            // Remove borrowed items from available items
            for (Member member : members) {
                for (LibraryItem borrowed : member.getBorrowedItems()) {
                    items.remove(borrowed);
                }
            }
        } catch (IOException e) {
            // File may not exist on first run, that's OK
        }
    }
}