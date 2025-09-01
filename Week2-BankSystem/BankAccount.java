public class BankAccount 
{
    private String accountHolderName;
    private String accountNumber;
    private double balance;

    public BankAccount(String accountHolderName, String accountNumber, double balance) 
    {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) 
    {
        if (amount > 0) 
        {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else 
        {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) 
    {
        if (amount > 0 && balance >= amount) 
        {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
        } else 
        {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public double checkBalance() 
    {
        return balance;
    }

    public String getAccountNumber() 
    {
        return accountNumber;
    }

    public String getAccountHolderName() 
    {
        return accountHolderName;
    }

    @Override
    public String toString() 
    {
        return "Account Number: " + accountNumber + ", Name: " + accountHolderName + ", Balance: " + balance;
    }

    public String toFileString() 
    {
        return accountNumber + "," + accountHolderName + "," + balance;
    }

    public static BankAccount fromFileString(String line) 
    {
        String[] parts = line.split(",");
        return new BankAccount(parts[1], parts[0], Double.parseDouble(parts[2]));
    }
}
