# ApexATM - Java ATM Simulator

This is a small ATM simulation I built in Java using Swing for the GUI. It's part of my "Build Your Own Project" submission, and the goal was pretty simple: recreate the core experience of using an ATM, but as a desktop app, while actually applying OOP concepts instead of just writing everything in one file.

## What it actually does
 
You log in with a user ID and a 4-digit PIN, and from there you land on a dashboard where you can:

- Check your balance
- Withdraw cash (including a few quick-cash buttons for common amounts)
- Deposit money
- Transfer funds to another user
- Change your PIN
- Look back at your past transactions

Nothing fancy in terms of banking logic, but I made sure the validation is solid - no negative amounts, no withdrawing more than you have, no garbage input sneaking through.

## Why I built it this way

I wanted the code to actually look like it was designed, not just thrown together, so I split things into three layers:

- **model** - `User` and `Transaction`, basically the data
- **service** - `AuthService` handles login, `AccountService` handles deposits/withdrawals/balance updates
- **ui** - the Swing panels (`LoginPanel` and `DashboardPanel`) that the user actually sees

Keeping the UI separate from the logic meant I could tweak how things looked without touching the banking rules, and vice versa. It also just made debugging way less painful.

For the interface itself I went for a darker, glassy card-style look instead of the usual boring gray Swing buttons - used custom `Graphics2D` drawing for rounded corners and that translucent panel effect.

## Setting it up and running it

There's no database, no build tool, and no external libraries to install here - it's plain Java with the built-in Swing library, so setup is mostly just making sure Java itself is ready on your machine.

### 1. Install the JDK

You need a Java Development Kit, version 8 or newer (I built and tested this on JDK 17, but anything from 8 up should compile fine since nothing here uses very recent language features).

- **Windows/Mac** - download from [Oracle's JDK page](https://www.oracle.com/java/technologies/downloads/) or grab [Eclipse Temurin](https://adoptium.net/) if you'd rather use an open-source build.
- **Linux** - easiest is through your package manager, e.g. `sudo apt install openjdk-17-jdk` on Ubuntu/Debian.

Once it's installed, confirm it worked by running:

```
java -version
javac -version
```

Both commands should print a version number. If they don't, Java either isn't installed or isn't on your system PATH - you may need to restart your terminal or add the JDK's `bin` folder to PATH manually.

### 2. Get the project files

Unzip `ApexATM_Project-main.zip` (or clone the repo, if you got it from a Git remote) wherever you'd like it. You should end up with a folder that looks like the layout further down in this README, with `src/atm/` inside it.

### 3. Dependencies

There aren't any. No Maven, no Gradle, no `pom.xml` or `build.gradle`, nothing to download. Java Swing and the standard library cover everything the app needs. This step exists mostly to say: you can skip it.

### 4. Configuration

Nothing needs to be configured either - no config files, no environment variables, no API keys. The two demo accounts (see the table below) are hardcoded directly in `AuthService.java`, so the app works right out of the box. If you want to change the starting balances or add more test users, that's the file to open.

### 5. Compile and run

**Option A - command line**

Open a terminal in the project's root folder, then:

```
cd src
javac atm/Main.java atm/model/*.java atm/service/*.java atm/ui/*.java
java atm.Main
```

The first command compiles everything, the second launches the app. A window titled "Automated Teller Machine" should pop up with the login screen.

**Option B - VS Code**

If you're using VS Code with the Java Extension Pack installed, just open the project folder - the `.vscode/launch.json` file is already set up, so you can hit the Run button (or press F5) and it'll compile and launch automatically.

**Option C - IntelliJ IDEA / Eclipse**

Import it as a plain Java project (not Maven/Gradle), point the source root at `src`, and run `Main.java` directly - both IDEs will detect the `main` method and give you a Run option next to it.

### 6. Log in and try it out

There are two test accounts built in so you don't have to set anything up yourself:

| User ID | PIN  | Starting Balance |
|---------|------|-------------------|
| user123 | 1234 | $1500.00 |
| admin   | 9999 | $5000.00 |

## A few things worth knowing

- All the data lives in memory only - close the app and it resets. There's no database or file storage hooked up, which was intentional since this was meant to be a self-contained simulation, not a production system.
- Amounts are stored as `double`, which is fine for a class project but I know it's not what you'd actually use for real money in production (floating point rounding issues and all that).
- The transaction history keeps track of type, amount, and the balance right after each transaction, along with a timestamp, so you can see exactly what happened and when.

## Project layout

```
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

## Possible next steps

If I keep working on this at some point, the things I'd add first are persistent storage (so balances survive a restart), support for registering new users instead of hardcoding two accounts, and maybe basic unit tests around the deposit/withdraw logic since right now it's only been tested manually by clicking through the app.
