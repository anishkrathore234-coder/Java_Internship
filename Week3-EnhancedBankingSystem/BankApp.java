import java.io.*;
import java.util.*;

public class BankApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        // Load accounts from file if exists
        try {
            bank.loadAccountsFromFile("accounts.dat");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data found, starting fresh.");
        }

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    applyInterest();
                    break;
                case 5:
                    checkBalance();
                    break;
                case 6:
                    bank.listAllAccounts();
                    break;
                case 7:
                    exitProgram();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Show the menu
    private static void showMenu() {
        System.out.println("\n--- Bank System Menu ---");
        System.out.println("1. Create new account");
        System.out.println("2. Deposit money");
        System.out.println("3. Withdraw money");
        System.out.println("4. Apply interest (Savings only)");
        System.out.println("5. Check balance");
        System.out.println("6. List all accounts");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    // Create a new account
    private static void createAccount() {
        System.out.print("Enter account type (Savings/Current): ");
        String type = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        System.out.print("Enter account number: ");
        String number = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        System.out.print("Enter extra field (Interest rate for Savings, Overdraft limit for Current): ");
        double extraField = scanner.nextDouble();
        scanner.nextLine();  // consume newline

        bank.createAccount(type, name, number, balance, extraField);
    }

    // Deposit money
    private static void depositMoney() {
        System.out.print("Enter account number: ");
        String number = scanner.nextLine();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // consume newline

        BankAccount account = bank.findAccountByNumber(number);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful. New balance: " + account.checkBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    // Withdraw money
    private static void withdrawMoney() {
        System.out.print("Enter account number: ");
        String number = scanner.nextLine();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // consume newline

        BankAccount account = bank.findAccountByNumber(number);
        if (account != null && account.withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: " + account.checkBalance());
        } else {
            System.out.println("Insufficient balance or account not found.");
        }
    }

    // Apply interest (for Savings accounts only)
    private static void applyInterest() {
        System.out.print("Enter account number: ");
        String number = scanner.nextLine();
        BankAccount account = bank.findAccountByNumber(number);
        if (account instanceof SavingsAccount) {
            SavingsAccount savingsAccount = (SavingsAccount) account;
            savingsAccount.applyInterest();
            System.out.println("Interest applied. New balance: " + savingsAccount.checkBalance());
        } else {
            System.out.println("Account is not a Savings account.");
        }
    }

    // Check balance
    private static void checkBalance() {
        System.out.print("Enter account number: ");
        String number = scanner.nextLine();
        BankAccount account = bank.findAccountByNumber(number);
        if (account != null) {
            System.out.println("Current balance: " + account.checkBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    // Exit the program and save accounts to file
    private static void exitProgram() {
        try {
            bank.saveAccountsToFile("accounts.dat");
            System.out.println("Accounts saved. Exiting program.");
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }
}
