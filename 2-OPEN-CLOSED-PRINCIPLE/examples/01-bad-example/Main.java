// ❌ BAD: every new payment method means editing PaymentService's if/else chain.
// The class is never "closed" — it's under constant modification pressure.
class PaymentService {
    public void pay(String type) {
        if (type.equals("card")) {
            System.out.println("Pay with card");
        } else if (type.equals("paypal")) {
            System.out.println("Pay with PayPal");
        } else if (type.equals("m10")) {
            System.out.println("Pay with M10");
        }
        // Adding "crypto" tomorrow means opening this method again,
        // and risking every branch that already works.
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentService payment = new PaymentService();
        payment.pay("m10");
    }
}

/*
 * Problems with this design:
 *  - Adding a new payment method = modifying existing, already-tested code
 *  - The more branches pile up, the higher the risk of breaking an unrelated one
 *  - PaymentService knows about every payment type instead of just "how to pay"
 */
