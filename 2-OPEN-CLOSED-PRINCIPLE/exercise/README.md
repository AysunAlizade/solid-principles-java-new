# OCP Practice Exercise — Customer Discount Calculator

## 🎯 Goal

Turn a growing `if`/`else` chain into something new discount tiers can plug into, without
touching existing, working code.

## 📦 Scenario

Marketing decides discount percentages per customer tier, and they add new tiers every
quarter. The current implementation, see [`Starter.java`](Starter.java):

```java
class DiscountCalculator {
    public double getDiscount(String customerType) {
        if (customerType.equals("regular")) {
            return 0.0;
        } else if (customerType.equals("premium")) {
            return 0.10;
        } else if (customerType.equals("vip")) {
            return 0.20;
        }
        return 0.0;
    }
}
```

Every new tier means editing `DiscountCalculator` again — and re-testing every branch that
already worked.

## 🛠️ Your task

Refactor so that adding a new customer tier means **adding a class**, not editing this one.

1. Define a `DiscountPolicy` interface with a method that returns the discount for an order
   amount (e.g. `double apply(double amount)` — some tiers may even want tier-specific logic
   later, like a cap, so design the method to take the amount, not just return a flat rate).
2. Implement one class per existing tier: `RegularCustomerDiscount`, `PremiumCustomerDiscount`,
   `VipCustomerDiscount`.
3. Write a `PricingService` (or similar) that takes a `DiscountPolicy` and applies it to an
   order total.
4. **Prove it's actually open for extension**: add a brand-new `StudentCustomerDiscount` (15%)
   without changing `PricingService` or any of the other tier classes.

## ✅ Requirements / acceptance criteria

- [ ] No `if`/`else` or `switch` on a "customer type" string anywhere in your final code
- [ ] `PricingService` (or equivalent) depends only on the `DiscountPolicy` interface
- [ ] Adding `StudentCustomerDiscount` required creating exactly one new file
- [ ] `main()` demonstrates at least four tiers (including the new one) applied to the same
      order amount, printing the discounted total for each

## 💡 Hint

If you ever catch yourself writing `instanceof` to figure out which discount to apply, you've
recreated the `if`/`else` chain with extra steps — the point is that the caller never needs to
know which concrete tier it's holding.

## Submitting your solution

See [Submitting your solutions](../../README.md#submitting-your-solutions) in the root
README for the branch/commit convention. Suggested commit message:

```
exercise: OCP - customer discount calculator
```
