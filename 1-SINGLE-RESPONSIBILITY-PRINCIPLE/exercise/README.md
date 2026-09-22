# SRP Practice Exercise — Invoice System

## 🎯 Goal

Take a single class that does everything and split it along its actual responsibilities.

## 📦 Scenario

A teammate wrote this `Invoice` class for a small internal billing tool. It "works," but
every future change — a new tax rule, a new export format, switching email providers — means
opening this same file.

See [`Starter.java`](Starter.java):

```java
class Invoice {
    private String customerName;
    private double[] itemPrices;

    public Invoice(String customerName, double[] itemPrices) {
        this.customerName = customerName;
        this.itemPrices = itemPrices;
    }

    public double calculateTotal() {
        double total = 0;
        for (double price : itemPrices) {
            total += price;
        }
        return total;
    }

    public void printInvoice() {
        System.out.println("Invoice for: " + customerName);
        for (double price : itemPrices) {
            System.out.println(" - $" + price);
        }
        System.out.println("Total: $" + calculateTotal());
    }

    public void saveToFile(String filename) {
        System.out.println("Saving invoice to " + filename);
    }

    public void emailInvoice(String emailAddress) {
        System.out.println("Emailing invoice to " + emailAddress);
    }
}
```

## 🔥 Spot the responsibilities

Before writing any code, list out loud what this one class is actually doing. You should find
at least four unrelated jobs mixed together.

## 🛠️ Your task

Refactor `Starter.java` so each responsibility lives in its own class:

- `Invoice` — a plain data holder: customer name + item prices, nothing else
- A calculator that computes the total from an `Invoice`
- A printer that formats and prints an `Invoice` (using the calculator for the total)
- A storage class that "saves" an `Invoice` (simulate it — a `System.out.println` is fine)
- A mailer class that "emails" an `Invoice` (simulate it the same way)

## ✅ Requirements / acceptance criteria

- [ ] `Invoice` has no `print`, `save`, or `email` methods left on it
- [ ] The total calculation lives in exactly one class, reusable by both printing and any
      future feature (discounts, reports, ...) without duplicating the loop
- [ ] Each new class has a name that describes *one* job, and no "and" in what it does
- [ ] A `main()` method wires the pieces together: build an `Invoice`, calculate its total,
      print it, save it, email it
- [ ] You can compute and check `calculateTotal()` for an `Invoice` without printing, saving,
      or emailing anything — that's your proof SRP was actually applied, not just renamed

## 💡 Hint

If you're not sure where a piece of logic belongs, ask: *"who would ask me to change this
code, and why?"* If the answer to that question is different for two pieces of logic in the
same class, they belong in different classes.

## Submitting your solution

See [Submitting your solutions](../../README.md#submitting-your-solutions) in the root
README for the branch/commit convention. Suggested commit message:

```
exercise: SRP - invoice system
```
