package atm;

import atm.model.User;
import atm.service.AccountService;
import atm.service.AuthService;
import atm.ui.DashboardPanel;
import atm.ui.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel container = new JPanel(cardLayout);
    private final LoginPanel loginPanel;
    private final DashboardPanel dashboardPanel;

    public Main() {
        setTitle("Automated Teller Machine");
        setSize(480, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        AuthService authService = new AuthService();
        AccountService accountService = new AccountService();

        loginPanel = new LoginPanel(this, authService);
        dashboardPanel = new DashboardPanel(this, accountService);

        container.add(loginPanel, "LOGIN");
        container.add(dashboardPanel, "DASHBOARD");

        add(container);
        showLogin();
    }

    public void showLogin() {
        cardLayout.show(container, "LOGIN");
    }

    public void showDashboard(User user) {
        dashboardPanel.setUser(user);
        cardLayout.show(container, "DASHBOARD");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
