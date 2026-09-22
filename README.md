# SOLID Principles in Java — Theory, Examples & Exercises

<p align="center">
  <img src="images/solid.webp" alt="SOLID Principles" width="600">
</p>

A structured set of Java exercises on the **SOLID** principles. Each lesson pairs theory with
runnable bad/good examples and its own practice exercise, capped off by a
[Complete Practice Exercise](./6-COMPLETE-PRACTICE-EXERCISE/) that combines all five. For
developers who already know OOP basics and are ready to learn how to *design* with them.

Part of a broader **Code Quality** track (Code Review, Clean Code, SOLID, Design Patterns) —
this repo covers SOLID in depth.

## Philosophy

Most resources don't give you stepwise, progressive exercises that actually build practice
with SOLID — they explain the theory and assume the rest will take care of itself once you're
working on real projects. That's often not true, or at best it takes a very long time: on a
real project you absorb SOLID indirectly, tangled up with operational concerns and tech-stack
noise, not through focused, deliberate practice. Without that structure, it's easy to stay
confused about SOLID for far longer than necessary.

This repo exists to fix that: each principle gets its own theory, a bad/good example pair you
run yourself, and its own practice exercise — plus a final exercise that forces you to apply
all five together, deliberately, before you ever have to reach for them under deadline
pressure on the job.

**SOLID is not a checklist to memorize. It's a set of answers to "why did this code become
hard to change?"**

Before jumping into Design Patterns, it's far more valuable to learn and practice **SOLID**
first — Design Patterns, Domain-Driven Design (DDD), and Clean Architecture are all built on
top of these five ideas. SOLID is harder to grasp than plain OOP, but once it actually clicks,
everything that comes after it gets dramatically easier to understand.

Every principle here exists to fix a concrete, recurring pain:

- Code that's scary to touch because one class does too much → **SRP**
- Every new feature means editing code that already worked → **OCP**
- A subclass that technically compiles but breaks its parent's promises → **LSP**
- An interface so bloated that half its methods are faked or throw → **ISP**
- Business logic welded to database/email/HTTP details, impossible to test → **DIP**

**Prerequisite: know OOP well first.** These principles assume you're already comfortable
with classes, interfaces, and polymorphism — SOLID is what comes *after* OOP, not instead of
it. If you need that foundation first, start with
[cpp-oop-examples-and-exercises](https://github.com/rusterman/cpp-oop-examples-and-exercises),
the companion OOP fundamentals repo this one follows in spirit and structure, then come back
here.

> **Don't just learn the five letters. Learn to recognize the pain each one solves — that's
> what lets you apply them without a cheat sheet.**

## Learning path

The material is organized progressively — each lesson builds toward the final, combined
exercise.

### [1. Single Responsibility Principle →](./1-SINGLE-RESPONSIBILITY-PRINCIPLE/)

A class should have only one reason to change. Splitting validation, persistence, and
notification out of one bloated `UserService` — then practicing it yourself on an invoicing
system.

### [2. Open/Closed Principle →](./2-OPEN-CLOSED-PRINCIPLE/)

Open for extension, closed for modification. Replacing an `if`/`else` payment dispatcher with
a `Payment` interface — then practicing it yourself on a customer discount calculator.

### [3. Liskov Substitution Principle →](./3-LISKOV-SUBSTITUTION-PRINCIPLE/)

Subtypes must be safely substitutable for their base type. A `FixedDepositAccount` that can't
honor `withdraw()` — then practicing it yourself on the classic `Square`/`Rectangle` problem.

### [4. Interface Segregation Principle →](./4-INTERFACE-SEGREGATION-PRINCIPLE/)

Don't force classes to implement methods they don't need. Splitting a fat
`MultiFunctionDevice` interface into `Printer`, `Scanner`, and `FaxMachine` — then practicing
it yourself on an employee payroll system.

### [5. Dependency Inversion Principle →](./5-DEPENDENCY-INVERSION-PRINCIPLE/)

Depend on abstractions, not concrete classes. Injecting a `MessageService` interface into
`Notification` — then practicing it yourself on a swappable order-storage backend.

### [6. Complete Practice Exercise →](./6-COMPLETE-PRACTICE-EXERCISE/)

Refactor a monolithic e-commerce checkout system by applying all five principles together, one
stage at a time.

## Setup and requirements

### A Java Development Kit (JDK)

Any JDK 17+ works. Examples use `javac`/`java` directly — no build tool required.

**macOS**

```bash
brew install openjdk@17
```

**Windows**

Download the installer from [adoptium.net](https://adoptium.net/) and run it, accepting the
defaults.

**Ubuntu / Debian**

```bash
sudo apt update && sudo apt install -y openjdk-17-jdk
```

Verify it worked (any platform):

```bash
java -version
javac -version
```

## Getting started

New to this? Follow these steps **in order** — one command block at a time, top to bottom.

### 1. Install Git

**macOS**

```bash
brew install git
```

**Windows**: download and run [git-scm.com/download/win](https://git-scm.com/download/win),
accepting the defaults.

**Ubuntu / Debian**

```bash
sudo apt update && sudo apt install -y git
```

### 2. Set up Git on your computer

Run this once, ever, on your machine (use the same email as your GitHub account):

```bash
git config --global user.name  "Your Name"
git config --global user.email "you@example.com"
```

### 3. Connect Git to your GitHub account

```bash
brew install gh    # Windows: winget install --id GitHub.cli   |   Linux: sudo apt install gh
gh auth login
```

Answer the prompts: **GitHub.com** → **HTTPS** → **Login with a web browser**. A page opens —
log in there, then come back to the terminal. Done, just once.

### 4. Fork this repository

Go to [github.com/rusterman/solid-principles-java](https://github.com/rusterman/solid-principles-java)
and click **Fork** (top-right button) → **Create fork**. This makes your own copy, under your
GitHub account, that you're allowed to write to.

### 5. Clone your fork to your computer

Replace `<your-username>` with your GitHub username:

```bash
git clone https://github.com/<your-username>/solid-principles-java.git
cd solid-principles-java
```

### 6. Connect the original repo, so you can pull future updates

```bash
git remote add upstream https://github.com/rusterman/solid-principles-java.git
```

### 7. Make your changes, then push them to your fork

Do this every time you finish an exercise:

```bash
git checkout -b solutions/<your-username>   # once, the first time
git add .
git commit -m "exercise: SRP - invoice system"
git push -u origin solutions/<your-username>
```

Your work is now saved on GitHub, in your fork, on a branch named after you.

## How to work through this guide

1. **Go in lesson order** — `1-SINGLE-RESPONSIBILITY-PRINCIPLE` → ... →
   `5-DEPENDENCY-INVERSION-PRINCIPLE` → `6-COMPLETE-PRACTICE-EXERCISE`. Each principle is
   easier to see clearly once you've internalized the one before it.
2. **Read the lesson's `README.md` first** — the idea, the bad example, the good example, and
   how to recognize the violation in code you didn't write.
3. **Run both examples.** Don't just read the diff — compile and execute `01-bad-example` and
   `02-good-example`, then change something and re-run it.
4. **Do the lesson's practice exercise** in `exercise/` before moving on — a *new* scenario,
   solved on your own (no reference solution ships in this repo — see
   [Submitting your solutions](#submitting-your-solutions) below).
5. **Finish with the Complete Practice Exercise.** Attempt each of its four refactor stages
   yourself, the same way.

### Build & run any example or exercise

```sh
cd 1-SINGLE-RESPONSIBILITY-PRINCIPLE/examples/02-good-example
javac Main.java && java Main
```

```sh
cd 1-SINGLE-RESPONSIBILITY-PRINCIPLE/exercise
javac Starter.java && java Starter
```

### Rules for learning

- **Don't just read the "good" example.** Compile and run the "bad" one first, and feel the
  problem before you see the fix.
- **Don't memorize the five names.** If you can explain *why* a piece of code is painful to
  change, you already understand the principle behind the fix.
- **Don't skip a lesson's exercise.** The bad/good example shows you *how*; the exercise, on a
  scenario you haven't seen before, tests whether you understand *why*.
- **Don't skip the Complete Practice Exercise.** The individual lessons show you *one*
  principle in isolation; this is where you practice recognizing *which* principle a real
  problem needs.

## Submitting your solutions

You already set up your `solutions/<your-username>` branch in
[Getting started](#7-make-your-changes-then-push-them-to-your-fork) — push to it every time
you finish an exercise. Commit each exercise separately, with a clear message:

- Per-principle exercises: `exercise: <PRINCIPLE> - <short description>` (e.g.
  `exercise: OCP - customer discount calculator`)
- Complete Practice Exercise stages: `final-exercise: stage <N> - <short description>` (e.g.
  `final-exercise: stage 1 - SRP refactor`)

**No answer key ships in this repo.** Every `exercise/` folder and the
`6-COMPLETE-PRACTICE-EXERCISE/` stages are meant to be solved by you — any `solution/` or
`solutions/` folder is excluded via [`.gitignore`](.gitignore) so it never reaches GitHub, on
this repo or your fork. Once you've genuinely attempted an exercise, compare notes with a
classmate, a code reviewer, or an LLM. Once pushed, you can open a pull request from
`solutions/<your-username>` into your own fork's `main` for a clean, reviewable diff of your
work.

---

The goal isn't just to name-drop "SOLID" in an interview — it's to look at a real class and
naturally ask: *what's the one reason this should change, and does the rest of this file
agree?* That's the difference between reciting principles and designing with them.

## Start learning

### [1. Single Responsibility Principle →](./1-SINGLE-RESPONSIBILITY-PRINCIPLE/)

### [2. Open/Closed Principle →](./2-OPEN-CLOSED-PRINCIPLE/)

### [3. Liskov Substitution Principle →](./3-LISKOV-SUBSTITUTION-PRINCIPLE/)

### [4. Interface Segregation Principle →](./4-INTERFACE-SEGREGATION-PRINCIPLE/)

### [5. Dependency Inversion Principle →](./5-DEPENDENCY-INVERSION-PRINCIPLE/)

### [6. Complete Practice Exercise →](./6-COMPLETE-PRACTICE-EXERCISE/)

## Contributors

| Contributor | Role |
|---|---|
| [Rustam Atakisiev](https://github.com/rusterman) | Creator & maintainer — also the author of the companion [cpp-oop-examples-and-exercises](https://github.com/rusterman/cpp-oop-examples-and-exercises) repository |

Want to contribute? Fork the repo, follow the [Submitting your solutions](#submitting-your-solutions)
workflow above, and open a pull request.
