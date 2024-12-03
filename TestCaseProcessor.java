import java.io.*;

public class TestCaseProcessor {

    private static final String TEST_CASE_FILE = "/Users/hrishirajdevsarmah/Developer/JavaDEV/PBL/src/test_cases.txt";

    public static void executeTestCases() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_CASE_FILE))) {
            String line;
            Account currentAccount = null;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String command = parts[0];

                switch (command) {
                    case "createAccount":
                        if (parts.length == 4) {
                            String username = parts[1];
                            String password = parts[2];
                            double balance = Double.parseDouble(parts[3]);
                            currentAccount = new Account(username, password, Account.generateAccountNumber(), balance);
                            FileHandler.saveUser(currentAccount);
                            System.out.println("Account created: " + username);
                        } else {
                            System.out.println("Invalid createAccount test case.");
                        }
                        break;

                    case "login":
                        if (parts.length == 3) {
                            String username = parts[1];
                            String password = parts[2];
                            currentAccount = FileHandler.authenticate(username, password);
                            if (currentAccount != null) {
                                System.out.println("Login successful for: " + username);
                            } else {
                                System.out.println("Login failed for: " + username);
                            }
                        } else {
                            System.out.println("Invalid login test case.");
                        }
                        break;

                    case "deposit":
                        if (currentAccount != null && parts.length == 2) {
                            double amount = Double.parseDouble(parts[1]);
                            Transaction.deposit(currentAccount, amount);
                        } else {
                            System.out.println("Invalid deposit test case or not logged in.");
                        }
                        break;

                    case "withdraw":
                        if (currentAccount != null && parts.length == 2) {
                            double amount = Double.parseDouble(parts[1]);
                            Transaction.withdraw(currentAccount, amount);
                        } else {
                            System.out.println("Invalid withdraw test case or not logged in.");
                        }
                        break;

                    case "transfer":
                        if (currentAccount != null && parts.length == 3) {
                            String recipientAccNo = parts[1];
                            double amount = Double.parseDouble(parts[2]);
                            Transaction.transfer(currentAccount, recipientAccNo, amount);
                        } else {
                            System.out.println("Invalid transfer test case or not logged in.");
                        }
                        break;

                    case "viewTransactions":
                        if (currentAccount != null) {
                            Transaction.viewTransactionHistory(currentAccount);
                        } else {
                            System.out.println("No user logged in for viewing transactions.");
                        }
                        break;

                    case "filterTransactions":
                        if (currentAccount != null && parts.length == 2) {
                            String filterType = parts[1];
                            Transaction.filterTransactionHistory(currentAccount);
                        } else {
                            System.out.println("Invalid filter test case or not logged in.");
                        }
                        break;

                    default:
                        System.out.println("Invalid command: " + command);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading test cases: " + e.getMessage());
        }
    }
}

