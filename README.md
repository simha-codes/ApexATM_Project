# ApexATM — ATM Simulation System

ApexATM is a Java Swing-based ATM simulation project developed as part of the VITyarthi **Build Your Own Project** curriculum.

The project simulates basic ATM operations such as user login, balance checking, cash withdrawal, quick cash, deposits, fund transfers, PIN changes, and transaction history.

The main purpose of the project is to apply **Java OOP concepts, GUI development, input validation, and basic service-based architecture** in a practical application.

---

## Features

### Login & Authentication
- User ID and PIN-based login
- Masked PIN input
- Invalid login detection
- Four-digit PIN validation

### Account Operations
- Check account balance
- Withdraw cash
- Quick Cash options
- Deposit cash
- Transfer money to another user
- Change ATM PIN

### Transaction History
- Records account transactions
- Stores transaction type, amount, and time
- Displays transaction history through the dashboard

### User Interface
- Built using Java Swing
- Modern glassmorphic-style interface
- Rounded cards and custom UI components
- Custom 2D graphics using `Graphics2D`
- Clean ATM dashboard layout

### Input Validation
The application handles common invalid inputs such as:

- Empty fields
- Invalid numbers
- Negative amounts
- Incorrect PIN format
- Insufficient balance
- Invalid transfer amounts

---

## Project Structure

```text
ApexATM_Project/
│
├── .vscode/
│   └── launch.json
│
├── src/
│   └── atm/
│       │
│       ├── model/
│       │   ├── User.java
│       │   └── Transaction.java
│       │
│       ├── service/
│       │   ├── AuthService.java
│       │   └── AccountService.java
│       │
│       ├── ui/
│       │   ├── LoginPanel.java
│       │   └── DashboardPanel.java
│       │
│       └── Main.java
│
├── README.md
└── statement.md