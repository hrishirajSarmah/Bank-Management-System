
/*public class BankManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Run Program Normally");
            System.out.println("2. Execute Test Cases");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Call the normal program flow (e.g., user login, transactions, etc.)
                    break;
                case 2:
                    TestCaseProcessor.executeTestCases();
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
*/
/*
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BankManager {
    public static void main(String[] args) {
        Scanner scanner = setupScanner(args);
        if (scanner == null) {
            System.out.println("Exiting program due to scanner setup failure.");
            return;
        }

        SessionManager.startSession();

        System.out.println("Welcome to the Bank Management System!");

        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Login");
            System.out.println("2. Create New Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> handleLogin(scanner);
                case 2 -> handleAccountCreation(scanner);
                case 3 -> {
                    System.out.println("Exiting program. Goodbye!");
                    exit = true;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static Scanner setupScanner(String[] args) {
        // Check if a test case file is passed as an argument
        if (args.length > 0) {
            try {
                File testCaseFile = new File(args[0]);
                return new Scanner(testCaseFile); // Read from the test case file
            } catch (FileNotFoundException e) {
                System.out.println("Test case file not found: " + args[0]);
                return null;
            }
        } else {
            return new Scanner(System.in); // Default to console input
        }
    }

    private static void handleLogin(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        Account account = FileHandler.authenticate(username, password);
        if (account != null) {
            System.out.println("Login successful!");
            SessionManager.showMenu(account, scanner);
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void handleAccountCreation(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.next();
        System.out.print("Enter a password: ");
        String password = scanner.next();
        System.out.print("Enter an initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();

        if (initialDeposit < 0) {
            System.out.println("Initial deposit cannot be negative. Account creation failed.");
            return;
        }

        String accountNumber = Account.generateAccountNumber(); // Generate a unique account number
        Account newAccount = new Account(username, password, accountNumber, initialDeposit);

        FileHandler.saveUser(newAccount); // Save the new account to the file
        System.out.println("Account created successfully! Your account number is: " + accountNumber);
    }
}
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BankManager {
    public static void main(String[] args) {
        Scanner scanner = initializeScanner();
        if (scanner == null) {
            System.out.println("Failed to initialize input. Exiting...");
            return;
        }

        SessionManager.startSession();

        System.out.println("Welcome to the Bank Management System!");

        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Login");
            System.out.println("2. Create New Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> handleLogin(scanner);
                case 2 -> handleAccountCreation(scanner);
                case 3 -> {
                    System.out.println("Exiting program. Goodbye!");
                    exit = true;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static Scanner initializeScanner() {
        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("Choose input mode:");
        System.out.println("1. Manual Input");
        System.out.println("2. Load from Test Case File");
        System.out.print("Enter your choice: ");
        int inputChoice = consoleScanner.nextInt();

        if (inputChoice == 1) {
            return consoleScanner;
        } else if (inputChoice == 2) {
            TestCaseProcessor.executeTestCases();
            return null;
//            System.out.print("Enter the test case file path: ");
//            String filePath = consoleScanner.next();
//            try {
//                File testCaseFile = new File(filePath);
//                return new Scanner(testCaseFile); // Read from the test case file
//            } catch (FileNotFoundException e) {
//                System.out.println("Test case file not found: " + filePath);
//                return null;
//            }
        } else {
            System.out.println("Invalid choice. Defaulting to manual input.");
            return consoleScanner;
        }
    }

    private static void handleLogin(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        Account account = FileHandler.authenticate(username, password);
        if (account != null) {
            System.out.println("Login successful!");
            SessionManager.showMenu(account, scanner);
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void handleAccountCreation(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.next();
        System.out.print("Enter a password: ");
        String password = scanner.next();
        System.out.print("Enter an initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();

        if (initialDeposit < 0) {
            System.out.println("Initial deposit cannot be negative. Account creation failed.");
            return;
        }

        String accountNumber = Account.generateAccountNumber(); // Generate a unique account number
        Account newAccount = new Account(username, password, accountNumber, initialDeposit);

        FileHandler.saveUser(newAccount); // Save the new account to the file
        System.out.println("Account created successfully! Your account number is: " + accountNumber);
    }
}

