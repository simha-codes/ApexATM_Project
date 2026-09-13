package atm.ui;

import atm.Main;
import atm.model.Transaction;
import atm.model.User;
import atm.service.AccountService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardPanel extends JPanel {
    private final Main mainFrame;
    private final AccountService accountService;
    private User currentUser;

    private final JLabel greetingLabel = new JLabel("Welcome back");
    private final JLabel dateStatusLabel = new JLabel();
    private final JLabel balanceDisplayLabel = new JLabel("Available: $0.00");

    public DashboardPanel(Main mainFrame, AccountService accountService) {
        this.mainFrame = mainFrame;
        this.accountService = accountService;

        // Use GridBagLayout to keep the kiosk frame strictly centered when maximized
        setLayout(new GridBagLayout());
        setOpaque(false);

        JPanel kioskFrame = new JPanel(new BorderLayout(0, 14));
        kioskFrame.setOpaque(false);
        kioskFrame.setPreferredSize(new Dimension(840, 520));
        kioskFrame.setMaximumSize(new Dimension(840, 520));

        // 1. Top Header Bar
        JPanel topHeader = new JPanel(new BorderLayout());
        topHeader.setOpaque(false);

        JLabel logoLabel = new JLabel("ATM Banking Terminal");
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logoLabel.setForeground(new Color(15, 23, 42));

        JPanel rightControls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 0));
        rightControls.setOpaque(false);

        updateDateTime();
        dateStatusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateStatusLabel.setForeground(new Color(100, 116, 139));

        JButton logoutBtn = createExitButton();
        logoutBtn.addActionListener(e -> {
            this.mainFrame.setSize(480, 560);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.showLogin();
        });

        rightControls.add(dateStatusLabel);
        rightControls.add(logoutBtn);

        topHeader.add(logoLabel, BorderLayout.WEST);
        topHeader.add(rightControls, BorderLayout.EAST);
        kioskFrame.add(topHeader, BorderLayout.NORTH);

        // 2. Center Frosted Card Container
        JPanel centerCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 195));
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
                g2.setColor(new Color(255, 255, 255, 240));
                g2.setStroke(new BasicStroke(1.2f));
                g2.draw(new RoundRectangle2D.Float(0.6f, 0.6f, getWidth() - 1.2f, getHeight() - 1.2f, 20, 20));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        centerCard.setOpaque(false);
        centerCard.setLayout(new BorderLayout(0, 14));
        centerCard.setBorder(new EmptyBorder(18, 22, 18, 22));

        // Subheader (Greeting + Balance)
        JPanel subHeader = new JPanel(new BorderLayout());
        subHeader.setOpaque(false);

        JPanel greetingBox = new JPanel();
        greetingBox.setLayout(new BoxLayout(greetingBox, BoxLayout.Y_AXIS));
        greetingBox.setOpaque(false);

        greetingLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        greetingLabel.setForeground(new Color(15, 23, 42));

        JLabel promptLabel = new JLabel("Please select your transaction");
        promptLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        promptLabel.setForeground(new Color(100, 116, 139));

        greetingBox.add(greetingLabel);
        greetingBox.add(Box.createVerticalStrut(2));
        greetingBox.add(promptLabel);

        balanceDisplayLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
        balanceDisplayLabel.setForeground(new Color(13, 148, 136));

        subHeader.add(greetingBox, BorderLayout.WEST);
        subHeader.add(balanceDisplayLabel, BorderLayout.EAST);
        centerCard.add(subHeader, BorderLayout.NORTH);

        // Grid Matrix
        JPanel gridContainer = new JPanel(new GridBagLayout());
        gridContainer.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton withdrawBtn = createHeroCard("↓", "Money", "Withdrawal");
        JButton depositBtn = createHeroCard("↑", "Money", "Deposit");

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.36; gbc.weighty = 0.5;
        gridContainer.add(withdrawBtn, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gridContainer.add(depositBtn, gbc);

        JPanel rightMatrix = new JPanel(new GridLayout(2, 3, 10, 10));
        rightMatrix.setOpaque(false);

        JButton balanceInquiryBtn = createTile("💲", "Balance Inquiry");
        JButton billPaymentBtn = createTile("📄", "Bill Payment");
        JButton miniStatementBtn = createTile("🧾", "Mini Statement");
        JButton internalTransferBtn = createTile("🌐", "Internal Transfer");
        JButton pinChangeBtn = createTile("🔢", "PIN Change");
        JButton quickCashBtn = createTile("⚡", "Quick Cash ($50)");

        rightMatrix.add(balanceInquiryBtn);
        rightMatrix.add(billPaymentBtn);
        rightMatrix.add(miniStatementBtn);
        rightMatrix.add(internalTransferBtn);
        rightMatrix.add(pinChangeBtn);
        rightMatrix.add(quickCashBtn);

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 2; gbc.weightx = 0.64; gbc.weighty = 1.0;
        gridContainer.add(rightMatrix, gbc);

        centerCard.add(gridContainer, BorderLayout.CENTER);
        kioskFrame.add(centerCard, BorderLayout.CENTER);

        // 3. Bottom Footer
        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);

        JLabel contact = new JLabel("📞 24/7 Helpline: 1800-000-111");
        contact.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        contact.setForeground(new Color(100, 116, 139));

        JLabel term = new JLabel("Automated Banking Terminal");
        term.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        term.setForeground(new Color(100, 116, 139));

        footer.add(contact, BorderLayout.WEST);
        footer.add(term, BorderLayout.EAST);
        kioskFrame.add(footer, BorderLayout.SOUTH);

        add(kioskFrame);

        // Event Bindings
        withdrawBtn.addActionListener(e -> handleWithdraw());
        depositBtn.addActionListener(e -> handleDeposit());
        balanceInquiryBtn.addActionListener(e -> showBalanceDialog());
        miniStatementBtn.addActionListener(e -> showMiniStatement());
        billPaymentBtn.addActionListener(e -> handleBillPayment());
        internalTransferBtn.addActionListener(e -> handleTransfer());
        pinChangeBtn.addActionListener(e -> handlePinChange());
        quickCashBtn.addActionListener(e -> handleQuickCash());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint bg = new GradientPaint(
                0, 0, new Color(241, 245, 249),
                getWidth(), getHeight(), new Color(224, 231, 255)
        );
        g2.setPaint(bg);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setPaint(new RadialGradientPaint(
                (float) getWidth() * 0.15f, (float) getHeight() * 0.2f, 380.0f,
                new float[]{0.0f, 1.0f},
                new Color[]{new Color(186, 230, 253, 150), new Color(241, 245, 249, 0)}
        ));
        g2.fillOval(-100, -80, 560, 480);

        g2.setPaint(new RadialGradientPaint(
                (float) getWidth() * 0.85f, (float) getHeight() * 0.85f, 400.0f,
                new float[]{0.0f, 1.0f},
                new Color[]{new Color(233, 213, 255, 160), new Color(224, 231, 255, 0)}
        ));
        g2.fillOval(getWidth() - 460, getHeight() - 420, 560, 480);

        g2.dispose();
    }

    private JButton createHeroCard(String icon, String line1, String line2) {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(30, 35, 48));
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setLayout(new GridBagLayout());
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(16, 18, 16, 18));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 6, 0, 14);

        JLabel badge = new JLabel(icon, SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 12, 12));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        badge.setPreferredSize(new Dimension(46, 46));
        badge.setFont(new Font("Segoe UI", Font.BOLD, 22));
        badge.setForeground(new Color(30, 35, 48));

        c.gridx = 0;
        btn.add(badge, c);

        JLabel text = new JLabel("<html><span style='font-family:Segoe UI; font-size:12px; color:#94A3B8;'>"
                + line1 + "<br><b style='font-size:16px; color:#FFFFFF;'>" + line2 + "</b></span></html>");
        c.gridx = 1;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        btn.add(text, c);

        return btn;
    }

    private JButton createTile(String icon, String title) {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.setColor(new Color(226, 232, 240));
                g2.setStroke(new BasicStroke(1.0f));
                g2.draw(new RoundRectangle2D.Float(0.5f, 0.5f, getWidth() - 1.0f, getHeight() - 1.0f, 14, 14));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setLayout(new GridBagLayout());
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(12, 8, 12, 8));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.6;
        c.anchor = GridBagConstraints.CENTER;

        JLabel iconBadge = new JLabel(icon, SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(30, 35, 48));
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        iconBadge.setPreferredSize(new Dimension(38, 38));
        iconBadge.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        iconBadge.setForeground(Color.WHITE);

        btn.add(iconBadge, c);

        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(new Color(30, 41, 59));
        c.gridy = 1;
        c.weighty = 0.4;
        btn.add(label, c);

        return btn;
    }

    private JButton createExitButton() {
        JButton btn = new JButton("Exit");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(new Color(255, 255, 255, 200));
        btn.setForeground(new Color(71, 85, 105));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
                new EmptyBorder(4, 14, 4, 14)
        ));
        return btn;
    }

    public void setUser(User user) {
        this.currentUser = user;
        greetingLabel.setText("Welcome back");
        updateBalanceDisplay();
    }

    private void updateDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy | HH:mm");
        dateStatusLabel.setText(LocalDateTime.now().format(dtf));
    }

    private void updateBalanceDisplay() {
        if (currentUser != null) {
            balanceDisplayLabel.setText(String.format("Available: $%.2f", currentUser.getBalance()));
        }
    }

    private void handleWithdraw() {
        String input = JOptionPane.showInputDialog(this, "Enter amount to withdraw ($):", "Money Withdrawal", JOptionPane.PLAIN_MESSAGE);
        if (input == null || input.trim().isEmpty()) return;
        try {
            double amt = Double.parseDouble(input.trim());
            if (accountService.withdraw(currentUser, amt)) {
                updateBalanceDisplay();
                JOptionPane.showMessageDialog(this, String.format("Please collect your cash: $%.2f", amt), "Dispensing Cash", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance or invalid amount!", "Transaction Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric value.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDeposit() {
        String input = JOptionPane.showInputDialog(this, "Enter deposit amount ($):", "Money Deposit", JOptionPane.PLAIN_MESSAGE);
        if (input == null || input.trim().isEmpty()) return;
        try {
            double amt = Double.parseDouble(input.trim());
            if (accountService.deposit(currentUser, amt)) {
                updateBalanceDisplay();
                JOptionPane.showMessageDialog(this, String.format("Successfully deposited: $%.2f", amt), "Deposit Confirmed", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Amount must be greater than zero.", "Deposit Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric value.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showBalanceDialog() {
        JOptionPane.showMessageDialog(this,
                String.format("Available Ledger Balance: $%.2f", currentUser.getBalance()),
                "Balance Inquiry", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showMiniStatement() {
        StringBuilder sb = new StringBuilder("=== RECENT TRANSACTIONS ===\n\n");
        if (currentUser.getHistory().isEmpty()) {
            sb.append("No transactions recorded during this session.\n");
        } else {
            for (Transaction tx : currentUser.getHistory()) {
                sb.append(tx.getFormattedRecord()).append("\n");
            }
        }
        sb.append("\nCurrent Ledger Balance: $").append(String.format("%.2f", currentUser.getBalance()));

        JTextArea area = new JTextArea(sb.toString(), 10, 36);
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, new JScrollPane(area), "Mini Statement", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleBillPayment() {
        String input = JOptionPane.showInputDialog(this, "Enter Utility Bill Amount ($):", "Bill Payment", JOptionPane.PLAIN_MESSAGE);
        if (input == null || input.trim().isEmpty()) return;
        try {
            double amt = Double.parseDouble(input.trim());
            if (accountService.withdraw(currentUser, amt)) {
                updateBalanceDisplay();
                JOptionPane.showMessageDialog(this, String.format("Bill payment of $%.2f processed successfully.", amt), "Payment Complete", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance!", "Payment Declined", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid numeric input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleTransfer() {
        String input = JOptionPane.showInputDialog(this, "Enter transfer amount ($):", "Internal Transfer", JOptionPane.PLAIN_MESSAGE);
        if (input == null || input.trim().isEmpty()) return;
        try {
            double amt = Double.parseDouble(input.trim());
            if (accountService.withdraw(currentUser, amt)) {
                updateBalanceDisplay();
                JOptionPane.showMessageDialog(this, String.format("Transferred $%.2f successfully.", amt), "Transfer Completed", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance!", "Transfer Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid numeric input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handlePinChange() {
        String newPin = JOptionPane.showInputDialog(this, "Enter new 4-digit PIN:", "PIN Change", JOptionPane.PLAIN_MESSAGE);
        if (newPin != null && newPin.trim().length() == 4 && newPin.trim().matches("\\d{4}")) {
            currentUser.setPin(newPin.trim());
            JOptionPane.showMessageDialog(this, "PIN successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if (newPin != null) {
            JOptionPane.showMessageDialog(this, "PIN must be exactly 4 digits.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleQuickCash() {
        if (accountService.withdraw(currentUser, 50.0)) {
            updateBalanceDisplay();
            JOptionPane.showMessageDialog(this, "Quick Cash dispensed: $50.00", "Dispensing Cash", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Insufficient balance for Quick Cash ($50.00)!", "Declined", JOptionPane.ERROR_MESSAGE);
        }
    }
}