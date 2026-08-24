# 🏢 Axis Residence Condominium Billing System

A console-based condominium billing management system built as a **Java OOP course final project — December 2024**.

Manages unit ownership, billing/dues (water, electricity, association fees), penalties, and payments for the Axis Residence condominium.

---

## 🚀 Features

### 👥 User Roles

| Role | Username | Password |
|------|----------|----------|
| 🔑 Admin | `AXIS_ADMIN` | `admin123` |
| 🏠 Homeowner | Owner ID (e.g. `OW-01`) | `user123` |

### 🔧 Admin Capabilities
- 🏗️ Add and manage condo units (floor, area, price, monthly installment)
- 👤 Add and manage unit owners (name, contact, email)
- 🔗 Register units to owners
- 🧾 Issue monthly bills (water, electricity, association dues)
- ⚠️ Issue penalties (10% of total unpaid bill)
- 📊 View all bills and payment status

### 🏠 Homeowner Capabilities
- 🏗️ View owned units
- 🧾 View own bills and payment status
- ✏️ View and edit contact details
- 💳 Issue payments against outstanding bills

### 💰 Billing System
- 📅 Auto-calculated due dates (14 days from issue date)
- 🧮 Total bill = Water + Electricity + Association + Monthly Installment + Penalty
- ✅ Exact-amount payment validation
- 📊 Payment status tracking (Paid/Unpaid)
- 🗃️ Default seed data on startup: 10 units, 6 owners, 10 due records

---

## 🛠️ Tech Stack

- 💻 **Language:** Java (Standard Library only)
- 💾 **Data Storage:** In-memory `HashMap` collections (no external database)
- 🖥️ **UI:** Console/CLI with formatted table output
- 📦 **Dependencies:** None
- 🔨 **IDE:** IntelliJ IDEA

---

## 📁 Project Structure

```
src/
├── main/
│   ├── Main.java              # 🚪 Entry point — login menu
│   ├── AdminUI.java           # 🔧 Admin dashboard & menus
│   └── OwnerUI.java           # 🏠 Homeowner dashboard & menus
├── model/
│   ├── Owner.java             # 👤 Condo unit owner
│   ├── Unit.java              # 🏢 Condo unit
│   ├── Due.java               # 🧾 Bill/invoice record
│   ├── Payment.java           # 💳 Payment transaction
│   ├── Paid.java              # ✅ Unit subclass (paid status)
│   └── Unpaid.java            # ❌ Unit subclass (unpaid status)
├── storage/
│   ├── Database.java          # 💾 Base class — static HashMap collections
│   ├── DBManager.java         # 🎛️ Central facade — delegates to all DB classes
│   ├── OwnerDB.java           # 👤 Owner CRUD operations
│   ├── UnitDB.java            # 🏢 Unit CRUD operations
│   ├── DueDB.java             # 🧾 Due/bill operations
│   └── PaymentDB.java         # 💳 Payment display operations
└── utility/
    ├── Util.java              # 🛠️ Shared UI helpers (input, validation, borders)
    ├── UserFunctions.java     # 🔐 Interface — login/mainPage contract
    ├── OwnerFunctions.java    # 👤 Interface — owner operations
    ├── UnitFunctions.java     # 🏢 Interface — unit operations
    ├── DueFunctions.java      # 🧾 Interface — due/bill operations
    └── PaymentFunctions.java  # 💳 Interface — payment operations
```

---

## 🎓 OOP Concepts Demonstrated

| Concept | Implementation |
|---------|---------------|
| 🔒 **Encapsulation** | All model classes use private fields with public getters/setters |
| 🧬 **Inheritance** | `Paid` and `Unpaid` extend `Unit`; DB classes extend `Database` |
| 🎭 **Polymorphism** | `instanceof` checks handle `Paid` vs `Unpaid` subtypes differently |
| 🎯 **Abstraction** | 5 interfaces define contracts implemented across multiple classes |
| 🧩 **Composition** | Owner → Unit → Due → Payment relational chain via nested HashMaps |
| ⚡ **Method Overloading** | Default seeding vs user-input constructors for units, owners, dues |
| 🔄 **toString() Override** | Every model class overrides `toString()` for formatted output |

---

## ▶️ How to Run

```bash
# 📦 Compile (from project root)
javac -d out src/model/*.java src/utility/*.java src/storage/*.java src/main/Main.java

# 🚀 Run
java -cp out main.Main
```

Or open in IntelliJ IDEA and run `Main.main()`.

---

## 📊 Data Model

```
Owner (OW-01) ──┬── Unit (AXRB-01) ──┬── Due (DUE-001) ── Payment (PAY-001)
                │                     │
                │                     └── Due (DUE-002)
                │
                └── Unit (AXRB-02) ──── Due (DUE-003)
```

All data is stored in memory via static HashMaps and resets on each program run.
