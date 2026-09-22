# 4 — Interface Segregation Principle (ISP)

![Interface Segregation Principle](../images/isp.png)

> Clients should not be forced to depend on methods they do not use.

## 💡 The idea

A "fat" interface that bundles many unrelated methods forces every implementer to provide
*all* of them, even the ones that make no sense for that particular class. The fix is to
split large interfaces into small, focused ones, so a class only ever implements the
capabilities it genuinely has.

ISP is basically SRP applied to interfaces: one interface, one cohesive capability.

## Use case

An office-equipment app models every device with one `MultiFunctionDevice` interface — but
not every device in the fleet is a multi-function machine:

- A modern all-in-one machine can **print**, **scan**, and **fax**
- An old printer can only **print**

## ❌ Bad example

```java
interface MultiFunctionDevice {
    void print(String document);
    void scan(String document);
    void fax(String document);
}

class OldPrinter implements MultiFunctionDevice {
    public void print(String document) {
        System.out.println("Printing: " + document);
    }

    public void scan(String document) {
        throw new UnsupportedOperationException("This printer can't scan");
    }

    public void fax(String document) {
        throw new UnsupportedOperationException("This printer can't fax");
    }
}
```

👉 `OldPrinter` is forced to implement `scan()` and `fax()` even though neither is meaningful
for it — the only options are to fake them or throw, and either one is a design smell.

## ✅ Good example

```java
interface Printer {
    void print(String document);
}

interface Scanner {
    void scan(String document);
}

interface FaxMachine {
    void fax(String document);
}

class OldPrinter implements Printer {
    public void print(String document) { System.out.println("Printing: " + document); }
}

class SmartOfficeMachine implements Printer, Scanner, FaxMachine {
    public void print(String document) { System.out.println("Printing: " + document); }
    public void scan(String document) { System.out.println("Scanning: " + document); }
    public void fax(String document) { System.out.println("Faxing: " + document); }
}
```

👉 `OldPrinter` implements only what it can actually do. Adding a `Stapler` capability later
won't force a single change onto it.

## How to spot a violation

- An implementation has a method body that's empty, throws, or returns a fake default just
  to satisfy the interface
- An interface keeps growing every time *any* implementer needs *anything* new
- Callers only ever use a handful of methods from a much larger interface

## Real-world mapping

ISP drives good **API design**: small, role-based interfaces (`Readable`, `Writable`,
`Closeable` in Java's own I/O library) compose better than one giant interface that tries to
do everything.

## Examples

| # | Focus |
|---|-------|
| 01 | One `MultiFunctionDevice` interface forcing `OldPrinter` to fake `scan()`/`fax()` (violation) |
| 02 | Split into `Printer` / `Scanner` / `FaxMachine` (fixed) |

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

Once the examples above make sense, apply ISP yourself on a fresh scenario:

### [Exercise: Employee Payroll System →](./exercise/)

Split a fat `Employee` interface so a `Contractor` is never forced to fake paid leave they
don't actually get.

## Next

### [5. Dependency Inversion Principle →](../5-DEPENDENCY-INVERSION-PRINCIPLE/)
