public class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    // Constructor
    public CurrentAccount(String accountHolder, String accountNumber, double balance, double overdraftLimit) {
        super(accountHolder, accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    // Withdraw money with overdraft limit
    @Override
    public boolean withdraw(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Overdraft Limit: " + overdraftLimit;
    }
}
