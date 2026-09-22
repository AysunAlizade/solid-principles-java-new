// ✅ GOOD: withdrawal is pulled out into its own capability. BankAccount no
// longer promises something not every account can deliver, so every
// substitution is safe.
interface Account {
    void deposit(double amount);
    double getBalance();
}

interface Withdrawable {
    void withdraw(double amount);
}

class SavingsAccount implements Account, Withdrawable {
    private double balance;

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ", balance: " + balance);
    }

    public void withdraw(double amount) {
        balance -= amount;
        System.out.println("Withdrew: " + amount + ", balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }
}

class FixedDepositAccount implements Account {
    private double balance;

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ", balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }

    // no withdraw() — and no obligation to have one
}

public class Main {
    // Code that needs to withdraw asks for Withdrawable, not Account —
    // so only accounts that CAN be withdrawn from are ever passed in.
    static void payMonthlyBill(Withdrawable account, double amount) {
        account.withdraw(amount);
    }

    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount();
        savings.deposit(1000);
        payMonthlyBill(savings, 200); // ✔️ compiles and works

        FixedDepositAccount fixedDeposit = new FixedDepositAccount();
        fixedDeposit.deposit(5000);
        // payMonthlyBill(fixedDeposit, 200); // ❌ won't even compile — caught before runtime!

        System.out.println("Fixed deposit balance: " + fixedDeposit.getBalance());
    }
}

/*
 * Why this is better:
 *  - Every Withdrawable passed to payMonthlyBill() is guaranteed to actually support it
 *  - FixedDepositAccount is still an Account — it just isn't forced into a contract
 *    it can't keep
 *  - The compiler catches the mistake instead of a customer hitting a runtime exception
 */
