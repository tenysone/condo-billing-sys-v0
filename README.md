# Axis Residence Condominium Billing System

A terminal-based condominium billing management system built in Java for the **OOP Course — December 2024**.

This is a pure console application — no GUI, no web interface. All interaction happens through text menus and formatted table output in the terminal. Data is stored entirely in-memory using Java `HashMap` collections.

---

## Features

### User Roles

| Role | Username | Password |
|------|----------|----------|
| Admin | `AXIS_ADMIN` | `admin123` |
| Homeowner | Owner ID (e.g. `OW-01`) | `user123` |

### Admin Capabilities
- Add and manage condo units (floor, area, price, monthly installment)
- Add and manage unit owners (name, contact, email)
- Register units to owners
- Issue monthly bills (water, electricity, association dues)
- Issue penalties (10% of total unpaid bill)
- View all bills and payment status

### Homeowner Capabilities
- View owned units
- View own bills and payment status
- View and edit contact details
- Issue payments against outstanding bills

### Billing System
- Auto-calculated due dates (14 days from issue date)
- Total bill = Water + Electricity + Association + Monthly Installment + Penalty
- Exact-amount payment validation
- Payment status tracking (Paid/Unpaid)
- Default seed data on startup: 10 units, 6 owners, 10 due records

---

## Data Storage — HashMap

This project uses **Java `HashMap`** as its sole data store. There is no external database, no SQL, no file persistence. All records live in memory and reset when the program exits.

### The Four HashMaps

Defined as `static` fields in `Database.java`, these are the backbone of the entire storage layer:

| HashMap | Key | Value | Purpose |
|---------|-----|-------|---------|
| `owners` | OwnerID (`String`) | `Owner` object | All registered condo owners |
| `units` | UnitNo (`String`) | `Unit` object | All condo units |
| `dues` | DueRefNo (`String`) | `Due` object | All issued bills/invoices |
| `payments` | PayRefNo (`String`) | `Payment` object | All payment transactions |

### How HashMap Is Used

- **Owner → Unit mapping:** Each `Owner` object contains its own `HashMap<String, Unit>` to track which units belong to that owner
- **Unit → Due mapping:** Each `Unit` object contains a `HashMap<String, Due>` to track all bills issued against that unit
- **Due → Payment mapping:** Each `Due` object holds a direct reference to a `Payment` object (one-to-one)
- **CRUD operations:** All create, read, update, and delete operations go through the static HashMaps in `Database.java` via the `DBManager` facade class
- **Lookup by ID:** Every entity is retrieved by its string key (e.g. `owners.get("OW-01")`), making lookups O(1)

This chain — `Owner` contains `HashMap<Unit>`, `Unit` contains `HashMap<Due>`, `Due` references `Payment` — forms the relational structure of the entire system.

---

## Tech Stack

- **Language:** Java (Standard Library only)
- **Data Storage:** In-memory `HashMap` collections
- **UI:** Terminal/CLI with formatted table output
- **Dependencies:** None
- **IDE:** IntelliJ IDEA

---

## Project Structure

```
src/
├── main/
│   ├── Main.java              # Entry point — login menu
│   ├── AdminUI.java           # Admin dashboard & menus
│   └── OwnerUI.java           # Homeowner dashboard & menus
├── model/
│   ├── Owner.java             # Condo unit owner
│   ├── Unit.java              # Condo unit
│   ├── Due.java               # Bill/invoice record
│   ├── Payment.java           # Payment transaction
│   ├── Paid.java              # Unit subclass (paid status)
│   └── Unpaid.java            # Unit subclass (unpaid status)
├── storage/
│   ├── Database.java          # Base class — holds the 4 static HashMaps
│   ├── DBManager.java         # Central facade — delegates to all DB classes
│   ├── OwnerDB.java           # Owner CRUD operations
│   ├── UnitDB.java            # Unit CRUD operations
│   ├── DueDB.java             # Due/bill operations
│   └── PaymentDB.java         # Payment display operations
└── utility/
    ├── Util.java              # Shared UI helpers (input, validation, borders)
    ├── UserFunctions.java     # Interface — login/mainPage contract
    ├── OwnerFunctions.java    # Interface — owner operations
    ├── UnitFunctions.java     # Interface — unit operations
    ├── DueFunctions.java      # Interface — due/bill operations
    └── PaymentFunctions.java  # Interface — payment operations
```

---

## OOP Concepts Demonstrated

| Concept | Implementation |
|---------|---------------|
| Encapsulation | All model classes use private fields with public getters/setters |
| Inheritance | `Paid` and `Unpaid` extend `Unit`; DB classes extend `Database` |
| Polymorphism | `instanceof` checks handle `Paid` vs `Unpaid` subtypes differently |
| Abstraction | 5 interfaces define contracts implemented across multiple classes |
| Composition | Owner → Unit → Due → Payment relational chain via nested HashMaps |
| Method Overloading | Default seeding vs user-input constructors for units, owners, dues |
| toString() Override | Every model class overrides `toString()` for formatted output |

---

## How to Run

```bash
# Compile (from project root)
javac -d out src/model/*.java src/utility/*.java src/storage/*.java src/main/Main.java

# Run
java -cp out main.Main
```

Or open in IntelliJ IDEA and run `Main.main()`.

---

## Data Model

```
Owner (OW-01) ──┬── Unit (AXRB-01) ──┬── Due (DUE-001) ── Payment (PAY-001)
                │                     │
                │                     └── Due (DUE-002)
                │
                └── Unit (AXRB-02) ──── Due (DUE-003)
```

All data is stored in memory via static HashMaps and resets on each program run.
