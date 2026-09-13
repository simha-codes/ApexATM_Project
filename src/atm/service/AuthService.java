package atm.service;

import atm.model.User;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private final Map<String, User> userDatabase = new HashMap<>();

    public AuthService() {
        userDatabase.put("user123", new User("user123", "1234", 1500.00));
        userDatabase.put("admin", new User("admin", "9999", 5000.00));
    }

    public User authenticate(String userId, String pin) {
        User user = userDatabase.get(userId);
        if (user != null && user.validatePin(pin)) {
            return user;
        }
        return null;
    }
}
