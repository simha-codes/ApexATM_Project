package atm.service;

import atm.model.Transaction;
import atm.model.User;
import java.util.UUID;

public class AccountService {

    public synchronized boolean deposit(User user, double amount) {
        if (amount <= 0) return false;
        double newBalance = user.getBalance() + amount;
        user.setBalance(newBalance);
        Transaction tx = new Transaction(
                UUID.randomUUID().toString().substring(0, 8),
                Transaction.Type.DEPOSIT,
                amount,
                newBalance
        );
        user.addTransaction(tx);
        return true;
    }

    public synchronized boolean withdraw(User user, double amount) {
        if (amount <= 0 || amount > user.getBalance()) return false;
        double newBalance = user.getBalance() - amount;
        user.setBalance(newBalance);
        Transaction tx = new Transaction(
                UUID.randomUUID().toString().substring(0, 8),
                Transaction.Type.WITHDRAWAL,
                amount,
                newBalance
        );
        user.addTransaction(tx);
        return true;
    }
}
