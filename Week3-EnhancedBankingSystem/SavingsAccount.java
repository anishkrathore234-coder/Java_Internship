public class SavingsAccount extends BankAccount {
    private double interestRate;

    // Constructor
    public SavingsAccount(String accountHolder, String accountNumber, double balance, double interestRate) {
        super(accountHolder, accountNumber, balance);
        this.interestRate = interestRate;
    }

    // Apply interest to the balance
    public void applyInterest() {
        balance += balance * interestRate / 100;
    }

    @Override
    public String toString() {
        return super.toString() + ", Interest Rate: " + interestRate + "%";
    }
}
