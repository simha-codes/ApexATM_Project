# Automated Teller Machine (ATM) Simulation System

A modular, multi-package Java desktop application developed according to the VITyarthi "Build Your Own Project" guidelines.

## Architecture
- `atm.model`: Data entities (`User.java`, `Transaction.java`).
- `atm.service`: Business logic (`AuthService.java`, `AccountService.java`).
- `atm.ui`: Graphical interfaces (`LoginPanel.java`, `DashboardPanel.java`).
- `atm.Main`: Entry point managing `CardLayout` state transitions.

## Default Credentials
- **User ID:** `user123` | **PIN:** `1234`
- **User ID:** `admin` | **PIN:** `9999`
