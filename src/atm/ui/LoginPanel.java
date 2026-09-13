package atm.ui;

import atm.Main;
import atm.model.User;
import atm.service.AuthService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class LoginPanel extends JPanel {
    private final JTextField userField = new JTextField(15);
    private final JPasswordField pinField = new JPasswordField(15);
    private final JButton loginBtn;
    private final AuthService authService;
    private final Main mainFrame;

    public LoginPanel(Main mainFrame, AuthService authService) {
        this.mainFrame = mainFrame;
        this.authService = authService;

        setLayout(new GridBagLayout());
        setOpaque(false);

        // Glassmorphism Floating Card Container
        JPanel glassCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Semi-transparent frosted glass fill
                g2.setColor(new Color(255, 255, 255, 175));
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 24, 24));

                // Thin luminous glass edge border
                g2.setColor(new Color(255, 255, 255, 220));
                g2.setStroke(new BasicStroke(1.2f));
                g2.draw(new RoundRectangle2D.Float(0.6f, 0.6f, getWidth() - 1.2f, getHeight() - 1.2f, 24, 24));

                g2.dispose();
                super.paintComponent(g);
            }
        };
        glassCard.setOpaque(false);
        glassCard.setLayout(new BoxLayout(glassCard, BoxLayout.Y_AXIS));
        glassCard.setBorder(new EmptyBorder(40, 42, 38, 42));

        // Heading with clean Segoe UI
        JLabel heading = new JLabel("ATM User Authentication", SwingConstants.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setForeground(new Color(15, 23, 42));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Enter your credentials to access your session");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setForeground(new Color(71, 85, 105));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input Labels and Translucent Fields
        JLabel userLabel = new JLabel("User ID");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        userLabel.setForeground(new Color(51, 65, 85));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleGlassInput(userField);

        JLabel pinLabel = new JLabel("4-Digit PIN");
        pinLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        pinLabel.setForeground(new Color(51, 65, 85));
        pinLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleGlassInput(pinField);

        // Copilot-inspired Gradient Action Button
        loginBtn = new JButton("Secure Login") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Copilot-style cyan-to-violet gradient
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(14, 165, 233),
                        getWidth(), getHeight(), new Color(99, 102, 241)
                );
                g2.setPaint(gp);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 12, 12));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setContentAreaFilled(false);
        loginBtn.setBorderPainted(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.setMaximumSize(new Dimension(280, 42));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel hint = new JLabel("Default: user123 / 1234");
        hint.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        hint.setForeground(new Color(100, 116, 139));
        hint.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Assembly
        glassCard.add(heading);
        glassCard.add(Box.createVerticalStrut(6));
        glassCard.add(subtitle);
        glassCard.add(Box.createVerticalStrut(26));
        glassCard.add(userLabel);
        glassCard.add(Box.createVerticalStrut(6));
        glassCard.add(userField);
        glassCard.add(Box.createVerticalStrut(14));
        glassCard.add(pinLabel);
        glassCard.add(Box.createVerticalStrut(6));
        glassCard.add(pinField);
        glassCard.add(Box.createVerticalStrut(24));
        glassCard.add(loginBtn);
        glassCard.add(Box.createVerticalStrut(14));
        glassCard.add(hint);

        add(glassCard);

        loginBtn.addActionListener(e -> handleLogin());
    }

    // Copilot-style radial/ambient gradient canvas
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Base atmospheric backdrop
        GradientPaint bgGradient = new GradientPaint(
                0, 0, new Color(241, 245, 249),
                getWidth(), getHeight(), new Color(224, 231, 255)
        );
        g2.setPaint(bgGradient);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Ambient glowing orb highlights (Copilot aura)
        g2.setPaint(new RadialGradientPaint(
                (float) getWidth() * 0.2f, (float) getHeight() * 0.25f, 260.0f,
                new float[]{0.0f, 1.0f},
                new Color[]{new Color(186, 230, 253, 160), new Color(241, 245, 249, 0)}
        ));
        g2.fillOval((int) (getWidth() * 0.05), (int) (getHeight() * 0.1), 400, 360);

        g2.setPaint(new RadialGradientPaint(
                (float) getWidth() * 0.85f, (float) getHeight() * 0.8f, 280.0f,
                new float[]{0.0f, 1.0f},
                new Color[]{new Color(233, 213, 255, 170), new Color(224, 231, 255, 0)}
        ));
        g2.fillOval((int) (getWidth() * 0.6), (int) (getHeight() * 0.5), 450, 420);

        g2.dispose();
    }

    private void styleGlassInput(JTextField field) {
        field.setMaximumSize(new Dimension(280, 38));
        field.setPreferredSize(new Dimension(280, 38));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setBackground(new Color(255, 255, 255, 200));
        field.setOpaque(true);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 230), 1),
                new EmptyBorder(6, 12, 6, 12)
        ));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void handleLogin() {
        String uid = userField.getText().trim();
        String pin = new String(pinField.getPassword()).trim();
        User user = authService.authenticate(uid, pin);
        if (user != null) {
            userField.setText("");
            pinField.setText("");
            mainFrame.showDashboard(user);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}