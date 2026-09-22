# ISP Practice Exercise — Employee Payroll System

## 🎯 Goal

Split a fat interface into focused ones, so a contractor is never forced to fake benefits
they don't actually receive.

## 📦 Scenario

A payroll system models every worker with one interface, see [`Starter.java`](Starter.java):

```java
interface Employee {
    double calculateSalary();
    void takePaidLeave(int days);
    void enrollInHealthInsurance();
}

class Contractor implements Employee {
    public double calculateSalary() {
        return 5000.0; // flat contract rate
    }

    public void takePaidLeave(int days) {
        throw new UnsupportedOperationException("Contractors don't get paid leave");
    }

    public void enrollInHealthInsurance() {
        throw new UnsupportedOperationException("Contractors aren't eligible for company insurance");
    }
}
```

`Contractor` gets paid, but the interface forces it to also "implement" paid leave and health
insurance — two benefits that, contractually, contractors never receive. Any code holding an
`Employee` reference now has to guess (or discover at runtime) which methods are actually
safe to call.

## 🛠️ Your task

Split `Employee` into small, focused interfaces:

- `Payable` — `calculateSalary()`
- `PaidLeaveEligible` — `takePaidLeave(int days)`
- `InsuranceEligible` — `enrollInHealthInsurance()`

Then:

1. `Contractor` implements only `Payable`.
2. Add a `FullTimeEmployee` that implements all three (`Payable`, `PaidLeaveEligible`,
   `InsuranceEligible`).
3. Write a helper `void runPayroll(Payable worker)` that works with **any** payable worker,
   contractor or full-time.

## ✅ Requirements / acceptance criteria

- [ ] No class has a method body that throws `UnsupportedOperationException` (or is otherwise
      a fake/empty implementation) just to satisfy an interface
- [ ] `Contractor` implements `Payable` only — it must be **impossible to compile** a call to
      `.takePaidLeave()` or `.enrollInHealthInsurance()` on a `Contractor` reference, not just
      impossible at runtime
- [ ] `FullTimeEmployee` implements `Payable`, `PaidLeaveEligible`, and `InsuranceEligible`
- [ ] `main()` demonstrates `runPayroll()` working for both `Contractor` and
      `FullTimeEmployee`, plus `FullTimeEmployee` also taking leave and enrolling in insurance

## 💡 Hint

You'll know you're done when the compiler itself — not a thrown exception — is what stops you
from calling `.takePaidLeave()` on a `Contractor`.

## Submitting your solution

See [Submitting your solutions](../../README.md#submitting-your-solutions) in the root
README for the branch/commit convention. Suggested commit message:

```
exercise: ISP - employee payroll system
```
