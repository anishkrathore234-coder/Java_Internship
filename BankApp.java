import java.util.Scanner;
 
public class BankApp
{

    public static void main(String[] args) 
    {

        Scanner scanner = new Scanner(System.in);
 
        // Create account with user details

        BankAccount myAccount = new BankAccount("Ajay", "IND123456", 1000.0);
 
        // Use getter methods to show personalized welcome message

        System.out.println("=========================================");

        System.out.println("  Welcome, " + myAccount.getAccountHolderName() + "!");

        System.out.println("  Your Account Number: " + myAccount.getAccountNumber());

        System.out.println("  Thank you for choosing UNITY Bank.");

        System.out.println("=========================================");
 
        int choice;

        do 
        {

            System.out.println("\n==== Main Menu ====");

            System.out.println("1. Deposit Money");

            System.out.println("2. Withdraw Money");

            System.out.println("3. Check Balance");

            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
 
            switch (choice) 
            {

                case 1:

                    System.out.print("Enter amount to deposit: Rs.");

                    double depositAmount = scanner.nextDouble();

                    myAccount.deposit(depositAmount);

                    break;

                case 2:

                    System.out.print("Enter amount to withdraw: Rs.");

                    double withdrawAmount = scanner.nextDouble();

                    myAccount.withdraw(withdrawAmount);

                    break;

                case 3:

                    myAccount.checkBalance();

                    break;

                case 4:

                    System.out.println("Thank you, " + myAccount.getAccountHolderName() + ", for banking with UNITY Bank!");

                    break;

                default:

                    System.out.println("Invalid choice. Please try again.");

            }

        } while (choice != 4);
 
        scanner.close();

    }

}

 