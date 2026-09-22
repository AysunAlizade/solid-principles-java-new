# DIP Practice Exercise — Swappable Order Storage

## 🎯 Goal

Free a high-level class from constructing its own low-level dependency, so the storage
backend can be swapped — including for tests — without touching the class that uses it.

## 📦 Scenario

See [`Starter.java`](Starter.java):

```java
class MySQLDatabase {
    public void save(String order) {
        System.out.println("Saving order to MySQL: " + order);
    }
}

class OrderProcessor {
    private MySQLDatabase database = new MySQLDatabase();

    public void process(String order) {
        System.out.println("Processing order: " + order);
        database.save(order);
    }
}
```

The team now wants to:

- Move production storage to MongoDB
- Use a fast, in-memory fake in unit tests — no real database required

With `OrderProcessor` constructing `MySQLDatabase` itself, both of those require editing
`OrderProcessor`'s source code.

## 🛠️ Your task

1. Define an `OrderRepository` interface with a `save(String order)` method.
2. Implement `MySQLOrderRepository`, `MongoOrderRepository`, and `InMemoryOrderRepository`
   (the in-memory one can just keep a `List<String>` of saved orders — that's your test
   double).
3. Change `OrderProcessor` to depend only on `OrderRepository`, received through its
   constructor — it should never construct a repository itself.

## ✅ Requirements / acceptance criteria

- [ ] `OrderProcessor` has no `new MySQLDatabase()` (or any concrete repository) anywhere in
      its source
- [ ] `OrderProcessor`'s constructor takes an `OrderRepository`
- [ ] `main()` processes an order using `InMemoryOrderRepository`, then processes another
      order using `MySQLOrderRepository` — **without changing a single line of
      `OrderProcessor`**
- [ ] After processing with `InMemoryOrderRepository`, you can read back the saved orders from
      it directly (proving it's genuinely usable as a test double, not just a println stand-in)

## 💡 Hint

If switching the database still means opening `OrderProcessor.java`, DIP hasn't actually been
applied yet — it's just been moved one file over. The real test is: can you add a fourth
storage backend (say, a JSON file) without `OrderProcessor` even being recompiled?

## Submitting your solution

See [Submitting your solutions](../../README.md#submitting-your-solutions) in the root
README for the branch/commit convention. Suggested commit message:

```
exercise: DIP - swappable order storage
```
