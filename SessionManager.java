import java.util.*;

/*public class SessionManager {
    private static final int TIMEOUT = 300; // 5 minutes
    private Timer timer = new Timer();

    public void startSession(Account account) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Session timed out. Please log in again.");
                System.exit(0);
            }
        }, TIMEOUT * 1000);

        while (true) {
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. View Balance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Filter Transaction History");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");
            int choice = main.scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    Transaction.deposit(account, main.scanner.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    Transaction.withdraw(account, main.scanner.nextDouble());
                    break;
                case 3:
                    System.out.print("Enter recipient account number: ");
                    String recipientAccNo = main.scanner.next();
                    System.out.print("Enter transfer amount: ");
                    Transaction.transfer(account, recipientAccNo, main.scanner.nextDouble());
                    break;
                case 4:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
                case 5:
                    Transaction.viewTransactionHistory(account);
                    break;
                case 6:
                    Transaction.filterTransactionHistory(account);
                    break;

                case 7:
                    System.out.println("Logging out...");
                    timer.cancel();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}*/

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class SessionManager {
    private static final int SESSION_TIMEOUT = 300000; // 5 minutes in milliseconds
    private static Timer timer;

    public static void startSession() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nSession timed out due to inactivity. Logging out...");
                System.exit(0);
            }
        }, SESSION_TIMEOUT);
    }

    public static void resetSession() {
        if (timer != null) {
            timer.cancel();
            startSession();
        }
    }

    public static void showMenu(Account account, Scanner scanner) {
        boolean activeSession = true;

        while (activeSession) {
            resetSession();
            System.out.println("\nWelcome, " + account.getUsername() + "!");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            resetSession(); // Reset session after input

            switch (choice) {
                case 1 -> deposit(account, scanner);
                case 2 -> withdraw(account, scanner);
                case 3 -> viewBalance(account);
                case 4 -> Transaction.viewTransactionHistory(account);
                case 5 -> {
                    System.out.println("Logging out...");
                    activeSession = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void deposit(Account account, Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            FileHandler.saveUser(account);
            FileHandler.logTransaction(account.getAccountNumber(), "Deposit", amount);
            System.out.println("Deposit successful! New balance: " + account.getBalance());
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    private static void withdraw(Account account, Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            FileHandler.saveUser(account);
            FileHandler.logTransaction(account.getAccountNumber(), "Withdrawal", amount);
            System.out.println("Withdrawal successful! New balance: " + account.getBalance());
        } else {
            System.out.println("Invalid amount or insufficient funds. Withdrawal failed.");
        }
    }

    private static void viewBalance(Account account) {
        System.out.println("Current balance: " + account.getBalance());
    }
}

