import java.io.*;
import java.util.Scanner;

public class BankApp 
{
    private static final String FILE_NAME = "accounts.txt";

    public static void main(String[] args) 
    {
        Bank bank = new Bank();
        loadAccounts(bank);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do 
        {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. List All Accounts");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) 
            {
                case 1 -> 
                {
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Account Number: ");
                    String number = scanner.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();
                    bank.createAccount(name, number, balance);
                }
                case 2 -> 
                {
                    System.out.print("Enter Account Number: ");
                    String accNum = scanner.nextLine();
                    BankAccount acc = bank.findAccountByNumber(accNum);
                    if (acc != null) {
                        System.out.print("Enter Deposit Amount: ");
                        acc.deposit(scanner.nextDouble());
                    } else {
                        System.out.println("Account not found.");
                    }
                }
                case 3 -> 
                {
                    System.out.print("Enter Account Number: ");
                    String accNum = scanner.nextLine();
                    BankAccount acc = bank.findAccountByNumber(accNum);
                    if (acc != null) 
                    {
                        System.out.print("Enter Withdrawal Amount: ");
                        acc.withdraw(scanner.nextDouble());
                    } else 
                    {
                        System.out.println("Account not found.");
                    }
                }
                case 4 -> 
                {
                    System.out.print("Enter Account Number: ");
                    String accNum = scanner.nextLine();
                    BankAccount acc = bank.findAccountByNumber(accNum);
                    if (acc != null) 
                    {
                        System.out.println("Current Balance: " + acc.checkBalance());
                    } else 
                    {
                        System.out.println("Account not found.");
                    }
                }
                case 5 -> bank.listAllAccounts();
                case 6 -> 
                {
                    saveAccounts(bank);
                    System.out.println("Exiting... Data saved.");
                }
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void saveAccounts(Bank bank) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) 
        {
            for (BankAccount acc : bank.getAccounts()) 
            {
                writer.write(acc.toFileString());
                writer.newLine();
            }
        } catch (IOException e) 
        {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    private static void loadAccounts(Bank bank) 
    {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                BankAccount acc = BankAccount.fromFileString(line);
                bank.getAccounts().add(acc);
            }
            System.out.println("Accounts loaded from file.");
        } catch (IOException e) 
        {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }
}
