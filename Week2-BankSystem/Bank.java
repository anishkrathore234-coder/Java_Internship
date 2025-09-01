import java.util.ArrayList;
import java.util.List;

public class Bank
 {
    private List<BankAccount> accounts;

    public Bank() 
    {
        accounts = new ArrayList<>();
    }

    public void createAccount(String name, String number, double initialBalance)
    {
        BankAccount account = new BankAccount(name, number, initialBalance);
        accounts.add(account);
        System.out.println("Account created successfully.");
    }

    public BankAccount findAccountByNumber(String accountNumber) 
    {
        for (BankAccount acc : accounts) 
        {
            if (acc.getAccountNumber().equals(accountNumber)) 
            {
                return acc;
            }
        }
        return null;
    }

    public void listAllAccounts() 
    {
        if (accounts.isEmpty()) 
        {
            System.out.println("No accounts found.");
        } else 
        {
            for (BankAccount acc : accounts) 
            {
                System.out.println(acc);
            }
        }
    }

    public List<BankAccount> getAccounts() 
    {
        return accounts;
    }
}
