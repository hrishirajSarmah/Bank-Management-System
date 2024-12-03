import java.io.*;

public class FileHandler {
    private static final String USER_FILE = "users.txt";
    private static final String TRANSACTION_FILE = "transactions.txt";

    public static void saveUser(Account account) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(account.getUsername() + "," + account.getPassword() + "," +
                    account.getAccountNumber() + "," + account.getBalance() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving user data.");
        }
    }

    public static Account authenticate(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    return new Account(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user data.");
        }
        return null;
    }

    public static void logTransaction(String accountNumber, String type, double amount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE, true))) {
            writer.write(accountNumber + "," + type + "," + amount + "\n");
        } catch (IOException e) {
            System.out.println("Error saving transaction data.");
        }
    }

    public static Account getAccountByNumber(String accountNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[2].equals(accountNumber)) {
                    return new Account(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading account data.");
        }
        return null;
    }
}

