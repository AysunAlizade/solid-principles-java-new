# 6 — Complete Practice Exercise: E-Commerce Checkout Refactor

<img src="../images/solid.webp" alt="SOLID Principles" width="100%">

## 🎯 Goal

Design and refactor a realistic e-commerce **payment + order system** by applying all five
SOLID principles, one at a time, to the same starter class.

You will:

1. Start from a bad, monolithic design
2. Identify *which* architectural problem maps to *which* SOLID principle
3. Refactor step-by-step: **SRP → OCP → LSP → ISP → DIP**
4. End with a clean, extensible, testable architecture

This is the exercise every previous lesson has been building toward. If you've done the
per-principle practice exercise in each of `1` through `5`, you've already applied each tool
on its own — this is the project where you use all five together, on one system, and learn to
recognize *which* principle a real problem needs without being told.

## 📦 Problem definition (realistic requirements)

You're building a backend system that must support:

**💳 Payments**
- Credit Card
- PayPal
- Bank Transfer
- Crypto (planned, future)

**📦 Orders**
- Create an order
- Calculate the total price
- Apply discounts (percentage / coupon)
- Save the order
- Send a confirmation email

**💸 Refunds**
- Some payment methods support refunds
- Some do **not** (e.g. crypto)

**📊 Logging & analytics**
- Log every payment
- Track failures
- Future: send events to an analytics system

## ❌ Step 0 — the starting point

This is your starter code — see [`Starter.java`](Starter.java):

```java
class OrderService {

    void checkout(String paymentType, double amount, String email) {

        // calculate discount
        if (amount > 1000) {
            amount = amount * 0.9;
        }

        // process payment
        if (paymentType.equals("credit")) {
            System.out.println("Processing credit card");
        } else if (paymentType.equals("paypal")) {
            System.out.println("Processing PayPal");
        } else if (paymentType.equals("bank")) {
            System.out.println("Processing bank transfer");
        }

        // save order
        System.out.println("Saving order to database");

        // send email
        System.out.println("Sending email to " + email);

        // logging
        System.out.println("Logging transaction");
    }

    void refund(String paymentType, double amount) {
        if (paymentType.equals("credit") || paymentType.equals("paypal")) {
            System.out.println("Refund processed");
        } else {
            throw new RuntimeException("Refund not supported");
        }
    }
}
```

## 🔥 Problems you must identify

This is the part interviewers actually care about — being able to *name* the violation, not
just "clean up the code."

| Principle | Violation in the code above |
|-----------|------------------------------|
| **SRP** | Discount calculation + payment processing + persistence + email + logging are all mixed into one method |
| **OCP** | Adding a new payment method means editing the `if`/`else` chain |
| **DIP** | `OrderService` depends on concrete logic (string comparisons, `System.out.println` calls), not abstractions |
| **ISP** | Refund logic is forced into the same flat structure as everything else, with no dedicated contract |
| **LSP** | Some payment types silently break behavior — `refund()` throws for types it wasn't designed to handle, instead of that being expressed in the type system |

Other real issues: no testability, no separation of concerns, impossible to scale, hard to
maintain.

## 🛠️ Your task

Refactor `OrderService` in five stages, one principle at a time.

| Stage | Principle | What to do |
|-------|-----------|-------------|
| 1 | **SRP** | Extract `DiscountService`, `PaymentService`, `OrderRepository`, `EmailService`, `LoggerService`. `OrderService` should only *orchestrate* the steps. |
| 2 | **OCP** | Introduce a `PaymentMethod` interface with `CreditCardPayment`, `PayPalPayment`, `BankPayment` implementations, so a new payment method never touches existing code. |
| 3 | **LSP + ISP** | Add a separate `RefundablePayment` interface. Only payment types that truly support refunds implement it (crypto/bank don't) — so an unsupported refund is a compile-time impossibility, not a runtime exception. |
| 4 | **DIP** | Make every collaborator of `OrderService` an interface (`DiscountPolicy`, `OrderRepository`, `NotificationService`, `Logger`, `PaymentService`), injected through the constructor. `OrderService` should not construct anything itself. |

> **No answer key in this repo.** Like every other exercise here, the reference solution for
> each stage isn't published — `solutions/` is listed in [`.gitignore`](../.gitignore), so it
> never leaves the maintainer's machine. The value of this exercise is in the struggle, not in
> reading someone else's `1-srp-solution.java`. Work through it, then compare notes with
> a classmate, a code reviewer, or an LLM once you're done.

## ✅ Self-check questions

After each stage, ask:

- If I add a new payment method today, how many existing files do I have to open?
- Can I unit test `OrderService.checkout()` without touching a real database or sending a
  real email?
- Is there any class doing two unrelated jobs at once?
- Could a caller pass in something that *compiles* but *breaks* at runtime?

## Build & run the starter

```sh
javac Starter.java -d /tmp/out
java -cp /tmp/out Main
```

## Previous

### [← 5. Dependency Inversion Principle](../5-DEPENDENCY-INVERSION-PRINCIPLE/)
