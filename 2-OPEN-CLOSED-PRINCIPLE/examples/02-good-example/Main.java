// ✅ GOOD: PaymentService is closed for modification, but the payment
// system stays open for extension — new methods just implement the interface.
interface Payment {
    void pay();
}

class CardPayment implements Payment {
    public void pay() {
        System.out.println("Pay with card");
    }
}

class PaypalPayment implements Payment {
    public void pay() {
        System.out.println("Pay with PayPal");
    }
}

class M10Payment implements Payment {
    public void pay() {
        System.out.println("Pay with M10");
    }
}

class PaymentService {
    public void process(Payment payment) {
        payment.pay();
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        paymentService.process(new M10Payment());

        // Adding crypto tomorrow needs ONE new class — zero changes here.
        Payment crypto = new Payment() {
            public void pay() {
                System.out.println("Pay with Crypto");
            }
        };
        paymentService.process(crypto);
    }
}

/*
 * Why this is better:
 *  - New payment method → add a new class implementing Payment. Done.
 *  - PaymentService never has to be touched, retested, or re-reviewed
 *  - Each Payment implementation is small, focused, and independently testable
 */
