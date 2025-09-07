import java.io.*;
import java.util.*;

public class Bank {
    private ArrayList<BankAccount> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    // Create a new account
    public void createAccount(String type, String name, String number, double balance, double extraField) {
        BankAccount account = null;
        if (type.equals("Savings")) {
            account = new SavingsAccount(name, number, balance, extraField);  // extraField is interest rate
        } else if (type.equals("Current")) {
            account = new CurrentAccount(name, number, balance, extraField);  // extraField is overdraft limit
        }
        if (account != null) {
            accounts.add(account);
        }
    }

    // Find account by account number
    public BankAccount findAccountByNumber(String number) {
        for (BankAccount account : accounts) {
            if (account.accountNumber.equals(number)) {
                return account;
            }
        }
        return null;
    }

    // List all accounts
    public void listAllAccounts() {
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }

    // Save accounts to a file
    public void saveAccountsToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(accounts);
        }
    }

    // Load accounts from a file
    public void loadAccountsFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            accounts = (ArrayList<BankAccount>) ois.readObject();
        }
    }
}
