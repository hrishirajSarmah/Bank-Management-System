import java.util.*;
import java.io.*;

public class Transaction {

    private static final String TRANSACTION_FILE = "transactions.txt";

    public static void deposit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        FileHandler.logTransaction(account.getAccountNumber(), "Deposit", amount);
        System.out.println("Deposit successful! Current Balance: " + account.getBalance());
    }

    public static void withdraw(Account account, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            FileHandler.logTransaction(account.getAccountNumber(), "Withdrawal", amount);
            System.out.println("Withdrawal successful! Current Balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public static void transfer(Account sender, String recipientAccNo, double amount) {
        Account recipient = FileHandler.getAccountByNumber(recipientAccNo);
        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        if (sender.getBalance() >= amount) {
            sender.setBalance(sender.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            FileHandler.logTransaction(sender.getAccountNumber(), "Transfer to " + recipientAccNo, amount);
            FileHandler.logTransaction(recipient.getAccountNumber(), "Transfer from " + sender.getAccountNumber(), amount);
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public static void viewTransactionHistory(Account account) {
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            boolean hasTransactions = false;
            System.out.println("\nTransaction History for Account: " + account.getAccountNumber());
            System.out.println("-------------------------------------------------");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(account.getAccountNumber())) {
                    System.out.println(parts[1] + " - Amount: " + parts[2]);
                    hasTransactions = true;
                }
            }
            if (!hasTransactions) {
                System.out.println("No transactions found.");
            }
            System.out.println("-------------------------------------------------\n");
        } catch (IOException e) {
            System.out.println("Error reading transaction history: " + e.getMessage());
        }
    }

    public static void filterTransactionHistory(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nFilter Transaction History for Account: " + account.getAccountNumber());
        System.out.println("1. Deposits");
        System.out.println("2. Withdrawals");
        System.out.println("3. Transfers");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        String filterType;
        switch (choice) {
            case 1:
                filterType = "Deposit";
                break;
            case 2:
                filterType = "Withdrawal";
                break;
            case 3:
                filterType = "Transfer";
                break;
            default:
                System.out.println("Invalid choice. Returning to menu.");
                return; // This is valid as it exits the method
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            boolean hasTransactions = false;
            System.out.println("\nFiltered Transactions (" + filterType + ") for Account: " + account.getAccountNumber());
            System.out.println("-------------------------------------------------");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(account.getAccountNumber()) && parts[1].contains(filterType)) {
                    System.out.println(parts[1] + " - Amount: " + parts[2]);
                    hasTransactions = true;
                }
            }
            if (!hasTransactions) {
                System.out.println("No transactions found for the selected filter.");
            }
            System.out.println("-------------------------------------------------\n");
        } catch (IOException e) {
            System.out.println("Error reading transaction history: " + e.getMessage());
        }
    }

}
