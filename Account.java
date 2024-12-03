import java.util.*;
import java.io.*;

import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    //private static final AtomicInteger ACCOUNT_NUMBER_GENERATOR = new AtomicInteger(10000); // Start from 10000
    private String username;
    private String password;
    private String accountNumber;
    private double balance;

    // Constructor
    public Account(String username, String password, String accountNumber, double balance) {
        this.username = username;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Generates a unique account number
//    public static String generateAccountNumber() {
//        return String.valueOf(ACCOUNT_NUMBER_GENERATOR.getAndIncrement());
//    }
    public static String generateAccountNumber() {
        return UUID.randomUUID().toString().substring(0, 8); // Generate a unique 8-character account number
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

