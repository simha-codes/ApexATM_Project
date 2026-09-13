package atm.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    public enum Type { DEPOSIT, WITHDRAWAL }

    private final String id;
    private final Type type;
    private final double amount;
    private final double postBalance;
    private final LocalDateTime timestamp;

    public Transaction(String id, Type type, double amount, double postBalance) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.postBalance = postBalance;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() { return id; }
    public Type getType() { return type; }
    public double getAmount() { return amount; }
    public double getPostBalance() { return postBalance; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public String getFormattedRecord() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %s: $%.2f | Balance: $%.2f",
                dtf.format(timestamp), type, amount, postBalance);
    }
}
