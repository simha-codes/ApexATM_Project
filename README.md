# ApexATM - Java ATM Simulator

ApexATM is a desktop ATM simulation developed in Java using Swing. The project demonstrates object-oriented programming concepts through a simple ATM workflow with login, account operations, validation, and transaction history.

This project was developed as part of the **VITyarthi - Build Your Own Project** submission.

## Overview

The application simulates the basic experience of using an ATM through a graphical user interface (GUI).

After logging in with a User ID and 4-digit PIN, the user can access the ATM dashboard and perform common account operations.

## Features

- User login with User ID and 4-digit PIN
- Balance enquiry
- Cash withdrawal
- Quick cash withdrawal
- Cash deposit
- Fund transfer to another user
- PIN change
- Transaction history
- Input validation
- Prevention of negative transaction amounts
- Prevention of withdrawals greater than the available balance
- GUI-based interaction using Java Swing

## Functional Modules

1. **Authentication Module** - validates the User ID and PIN.
2. **Account Management Module** - handles balance enquiry, deposits, withdrawals, and transfers.
3. **Transaction Module** - records transaction details and displays transaction history.
4. **User Interface Module** - provides the login screen and ATM dashboard.

## Technologies and Tools

- **Language:** Java
- **GUI:** Java Swing
- **JDK:** JDK 8 or later
- **Version Control:** Git / GitHub
- **IDE (optional):** VS Code, IntelliJ IDEA, or Eclipse
- **External Libraries:** None
- **Database:** None
- **Build Tool:** None

## Prerequisites

Before running the project, install:

- Java Development Kit (JDK) 8 or later
- Git, if cloning the repository from GitHub
- A terminal/command prompt

The project was tested during development with JDK 17.

Verify Java installation:

```bash
java -version
javac -version
```

Both commands should display the installed Java version.

## Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/simha-codes/ApexATM_Project.git
cd ApexATM_Project
```

### 2. Check the Project Structure

```text
ApexATM_Project/
├── .vscode/
│   └── launch.json
├── src/
│   └── atm/
│       ├── Main.java
│       ├── model/
│       │   ├── User.java
│       │   └── Transaction.java
│       ├── service/
│       │   ├── AuthService.java
│       │   └── AccountService.java
│       └── ui/
│           ├── LoginPanel.java
│           └── DashboardPanel.java
├── statement.md
└── README.md
```

## Execution Commands

The project can be compiled and executed directly from the terminal without an IDE.

### 1. Create the output directory

From the project root:

```bash
mkdir bin
```

### 2. Compile

```bash
javac -d bin src/atm/model/*.java src/atm/service/*.java src/atm/ui/*.java src/atm/Main.java
```

### 3. Run

```bash
java -cp bin atm.Main
```

The ATM GUI should open after the run command.

## Test Accounts

| User ID | PIN | Starting Balance |
|---|---:|---:|
| `user123` | `1234` | `$1500.00` |
| `admin` | `9999` | `$5000.00` |

## Testing Instructions

Testing can be performed manually through the GUI after compiling and running the application.

### Authentication Tests

- Use a valid User ID and PIN and verify that the dashboard opens.
- Use an incorrect PIN and verify that login is rejected.
- Use an invalid User ID and verify that login is rejected.

### Account Operation Tests

- Check the current balance.
- Deposit a valid amount and verify the balance update.
- Withdraw a valid amount and verify the balance update.
- Try to withdraw more than the available balance.
- Try to enter a negative amount.
- Transfer a valid amount to another available user.
- Change the PIN and verify the new PIN during login.

### Transaction Tests

- Perform a deposit, withdrawal, or transfer.
- Open transaction history.
- Verify the transaction type, amount, resulting balance, and timestamp.

> **Testing status:** Manual test cases are provided above. Individual test results should be verified during project demonstration before being marked as passed.

## Running with an IDE

### VS Code

1. Install the Java Extension Pack.
2. Open the project folder.
3. Open `src/atm/Main.java`.
4. Run the `main` method or press **F5**.

The repository contains `.vscode/launch.json` for the project.

### IntelliJ IDEA / Eclipse

Import the project as a plain Java project, use `src` as the source folder, and run `Main.java`.

## Architecture

The project uses a simple layered package structure:

### Model Layer

- `User.java` - stores user/account information and transaction history.
- `Transaction.java` - represents transaction records.

### Service Layer

- `AuthService.java` - handles authentication and user management.
- `AccountService.java` - handles deposits, withdrawals, transfers, and balance updates.

### UI Layer

- `LoginPanel.java` - login interface.
- `DashboardPanel.java` - main ATM interface.

### Main Application

- `Main.java` - starts the application and controls the main UI flow.

This separation keeps UI code, application logic, and data classes organized into different packages.

## Data Storage

ApexATM does not use a database or file-based storage. User and transaction information is maintained in memory while the application is running.

When the application is closed, the data resets to the initial demo-account state.

## Design Decisions

- **Java Swing:** used to create the desktop GUI using Java's standard libraries.
- **Layered package structure:** separates model, service, and UI responsibilities.
- **In-memory storage:** keeps the educational simulation simple and self-contained.
- **Input validation:** prevents invalid account operations.
- **Git/GitHub:** used for version control and project submission.

## Limitations

- Data is not persistent and is lost when the application closes.
- Demo users are predefined in the application.
- The project is an educational ATM simulation and is not intended for real banking use.
- Money values use `double`, which is acceptable for this academic project but is not recommended for production financial systems.

## Future Enhancements

- Database-based persistent storage
- User registration
- Secure PIN storage
- Unit tests for account operations
- Improved account and transaction management
- Additional ATM services

## Repository Contents

The repository includes:

- `README.md` - project overview, features, setup, execution, and testing instructions.
- `statement.md` - problem statement, scope, target users, and high-level features.
- `src/` - organized Java source code.
- `.vscode/` - optional VS Code launch configuration.

## Project Repository

**GitHub:** `simha-codes/ApexATM_Project`

