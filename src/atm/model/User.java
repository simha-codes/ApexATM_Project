package atm.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userId;
    private String pin;
    private double balance;
    private final List<Transaction> history;

    public User(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = initialBalance;
        this.history = new ArrayList<>();
    }

    public String getUserId() { return userId; }
    public boolean validatePin(String pinInput) { return this.pin.equals(pinInput); }
    public void setPin(String newPin) { this.pin = newPin; }
    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }
    public List<Transaction> getHistory() { return history; }
    public void addTransaction(Transaction tx) { history.add(tx); }
}