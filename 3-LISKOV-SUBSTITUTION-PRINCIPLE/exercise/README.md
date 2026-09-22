# LSP Practice Exercise — Shape Area Calculator

## 🎯 Goal

Fix the single most famous LSP violation in OOP — `Square extends Rectangle` — so that code
written against the base type behaves correctly for every subtype, every time.

## 📦 Scenario

See [`Starter.java`](Starter.java):

```java
class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public int getArea() { return width * height; }
}

class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width; // forces height to match width
    }

    @Override
    public void setHeight(int height) {
        this.width = height;
        this.height = height;
    }
}
```

Mathematically a square *is* a rectangle. But watch what happens to code written against
`Rectangle`:

```java
Rectangle rect = new Square();
rect.setWidth(5);
rect.setHeight(10);
System.out.println(rect.getArea()); // expected 50, actually 100
```

Anyone who writes `setWidth` then `setHeight` on a `Rectangle` reasonably expects independent
control over both dimensions. `Square` silently breaks that expectation the moment it's
substituted in — exactly what LSP forbids.

## 🛠️ Your task

Redesign the shapes so this kind of surprise is **impossible**, not just avoided by
convention. You have two valid paths — pick one and justify it:

- **Option A — no inheritance between them.** Both `Rectangle` and `Square` implement a
  common `Shape` interface (`getArea()`), but `Square` does not extend `Rectangle`. Each has
  its own constructor with only the parameters it actually needs (`Square(int side)`,
  `Rectangle(int width, int height)`).
- **Option B — remove the broken contract.** If you keep a hierarchy, make shapes immutable
  (no `setWidth`/`setHeight` at all — dimensions are set once, in the constructor). Then
  `Square` extending `Rectangle` can no longer violate anything, because there's no mutator
  left to override unsafely.

## ✅ Requirements / acceptance criteria

- [ ] There is no code path where calling public methods on a `Shape` reference produces a
      result the caller couldn't have predicted from the base type alone
- [ ] Write `void printArea(Shape shape)` that computes and prints the area — it must work
      correctly for both a `Rectangle` and a `Square`, with **no `instanceof` checks**
- [ ] `main()` demonstrates both shapes through `printArea`, with correct, independent areas
- [ ] Add a short comment explaining *why* you picked Option A or B over the other

## 💡 Hint

The bug isn't that `Square` extends `Rectangle` — it's that `Rectangle` exposes a capability
("set width and height independently, at any time") that `Square` cannot honestly support.
Fixing LSP is usually about shrinking the base type's promises to what every subtype can
actually keep, not about clever overrides.

## Submitting your solution

See [Submitting your solutions](../../README.md#submitting-your-solutions) in the root
README for the branch/commit convention. Suggested commit message:

```
exercise: LSP - shape area calculator
```
