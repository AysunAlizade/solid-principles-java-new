# 3 — Liskov Substitution Principle (LSP)

![Liskov Substitution Principle](../images/lsp.webp)

> Subtypes must be substitutable for their base types, without breaking the correctness of
> the program.

## 💡 The idea

If `SavingsAccount extends BankAccount`, then any code that works with a `BankAccount` must
keep working, without modification, when handed a `SavingsAccount` instead. LSP is not about
the type system letting you substitute one class for another (the compiler already guarantees
that) — it's about the **behavior** staying correct when you do.

A classic red flag: a subclass that overrides a method just to throw an exception, return a
dummy value, or do nothing. That subclass technically *is-a* base type, but it silently
breaks the base type's contract.

## Use case

A banking app models several account types with one base class: `BankAccount`, with
`deposit()`, `withdraw()`, and `getBalance()`. A `FixedDepositAccount` — a term deposit that
can't be touched until it matures — is added as a subclass, since it "is a" bank account too.

## ❌ Bad example

```java
class BankAccount {
    protected double balance;

    public void deposit(double amount) { balance += amount; }
    public void withdraw(double amount) { balance -= amount; }
    public double getBalance() { return balance; }
}

class FixedDepositAccount extends BankAccount {
    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Cannot withdraw before maturity date");
    }
}
```

👉 Any code written against `BankAccount` (e.g. a billing job that calls
`account.withdraw(amount)` to collect a payment) now crashes the moment it meets a
`FixedDepositAccount`. The substitution is unsafe.

## ✅ Good example

```java
interface Account {
    void deposit(double amount);
    double getBalance();
}

interface Withdrawable {
    void withdraw(double amount);
}

class SavingsAccount implements Account, Withdrawable {
    private double balance;
    public void deposit(double amount) { balance += amount; }
    public void withdraw(double amount) { balance -= amount; }
    public double getBalance() { return balance; }
}

class FixedDepositAccount implements Account {
    private double balance;
    public void deposit(double amount) { balance += amount; }
    public double getBalance() { return balance; }
    // no withdraw() — and no obligation to have one
}
```

👉 Withdrawal becomes its own capability (`Withdrawable`). Code that needs to withdraw funds
asks for a `Withdrawable`, so only accounts that actually support it are ever passed in — a
`FixedDepositAccount` can't even compile its way into that code path.

## How to spot a violation

- An override that throws `UnsupportedOperationException`, returns `null`/`0`/`false` as a
  no-op, or silently does nothing where the base type promised real behavior
- A subclass that strengthens preconditions (demands more from the caller) or weakens
  postconditions (promises less back) than its base type
- Client code that checks `instanceof` to special-case one particular subtype

## Real-world mapping

LSP is what separates **correct inheritance modeling** from inheritance used purely for code
reuse. "Is a `FixedDepositAccount` a `BankAccount`?" conceptually, yes — but modeling it that
way only works if every operation you put on `BankAccount` is one every account can actually
perform. This is exactly the same reasoning behind the `RefundablePayment` split in the
[Complete Practice Exercise →](../6-COMPLETE-PRACTICE-EXERCISE/): not every payment method
supports refunds, so refund isn't put on the base contract either.

## Examples

| # | Focus |
|---|-------|
| 01 | `FixedDepositAccount extends BankAccount` and breaks `withdraw()` (violation) |
| 02 | Withdrawal pulled into a `Withdrawable` interface (fixed) |

## Build & run

```sh
cd examples/01-bad-example
javac Main.java && java Main
```

```sh
cd examples/02-good-example
javac Main.java && java Main
```

## 🏋️ Practice exercise

Once the examples above make sense, apply LSP yourself on a fresh scenario:

### [Exercise: Shape Area Calculator →](./exercise/)

Fix the classic `Square extends Rectangle` violation so substituting one shape for another
can never silently produce the wrong area.

## Next

### [4. Interface Segregation Principle →](../4-INTERFACE-SEGREGATION-PRINCIPLE/)
