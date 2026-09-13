# Problem Statement: Automated Teller Machine (ATM) Simulation System

## 1. Problem Statement
Physical and software-driven banking kiosks require robust transaction integrity, reliable data flow, and intuitive visual interfaces to prevent manual handling errors and unauthorized operations. This system provides a simulated software ATM client built using modular Object-Oriented Design (OOD) in Java, ensuring decoupled business logic and secure state execution.

## 2. Scope of the Project
- Authenticate registered bank account holders via PIN credentials.
- Execute basic core transactions (balance check, deposit, debit/withdrawal).
- Maintain an immutable, in-memory session audit trail per user.
- Enforce strict input validation, zero-overdraft prevention, and session termination.

## 3. Target Users
- Retail banking customers seeking self-service transactions.
- Academic evaluators analyzing Java Swing desktop UI, Separation of Concerns (SoC), and Object-Oriented design patterns.

## 4. High-Level Features
- **Security & Session Gatekeeper:** Card/ID authentication with PIN validation.
- **Transactional Consistency:** State validation preventing negative values and overdraft conditions.
- **Dynamic Ledger Logging:** Real-time chronological transaction history recording.
