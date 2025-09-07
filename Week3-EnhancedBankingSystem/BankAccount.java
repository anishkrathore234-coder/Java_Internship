import java.io.Serializable;

public class BankAccount implements Serializable {
    protected String accountHolder;
    protected String accountNumber;
    protected double balance;

    // Constructor
    public BankAccount(String accountHolder, String accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Deposit money
    public void deposit(double amount) {
        balance += amount;
    }

    // Withdraw money
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    // Check balance
    public double checkBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Holder: " + accountHolder + ", Balance: " + balance;
    }
}
