// ❌ BAD: FixedDepositAccount IS-A BankAccount, but it cannot honor
// BankAccount's contract. Anywhere code expects "an account you can withdraw
// from", a FixedDepositAccount blows it up.
class BankAccount {
    protected double balance;

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

class FixedDepositAccount extends BankAccount {
    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Cannot withdraw before maturity date");
    }
}

public class Main {
    // Code written against BankAccount reasonably assumes withdraw() works.
    static void payMonthlyBill(BankAccount account, double amount) {
        account.withdraw(amount);
    }

    public static void main(String[] args) {
        BankAccount savings = new BankAccount();
        savings.deposit(1000);
        payMonthlyBill(savings, 200);

        BankAccount fixedDeposit = new FixedDepositAccount();
        fixedDeposit.deposit(5000);
        payMonthlyBill(fixedDeposit, 200); // 💥 throws at runtime
    }
}

/*
 * Problems with this design:
 *  - FixedDepositAccount is substitutable in the TYPE SYSTEM but not in BEHAVIOR
 *  - Any code that pays a bill, transfers funds, or processes a refund by calling
 *    withdraw() now has to special-case FixedDepositAccount
 *  - The inheritance relationship models "what a FixedDepositAccount technically is",
 *    not "what a FixedDepositAccount can actually do"
 */
