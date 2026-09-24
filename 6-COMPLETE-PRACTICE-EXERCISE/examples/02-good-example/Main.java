public class Main {

    public static void main(String[] args) {

        // COMMON SERVICES
      
        DiscountService discountService = new DiscountService();
        OrderRepository orderRepository = new OrderRepository();
        EmailService emailService = new EmailService();
        PaymentLogger paymentLogger = new PaymentLogger();

        // CREDIT CARD
     
        PaymentProcessor creditCardPayment = new CreditCardPayment();

        OrderService creditCardOrder = new OrderService(creditCardPayment, discountService, orderRepository, emailService, paymentLogger);

        creditCardOrder.checkout(1500,"customer@gmail.com" );

        System.out.println();

        // PAYPAL

        PaymentProcessor paypalPayment = new PayPalPayment();

        OrderService paypalOrder = new OrderService( paypalPayment, discountService,  orderRepository,  emailService, paymentLogger);

        paypalOrder.checkout( 800, "customer@gmail.com" );

        System.out.println();

        // BANK TRANSFER

        PaymentProcessor bankPayment = new BankTransferPayment();

        OrderService bankOrder = new OrderService( bankPayment, discountService, orderRepository, emailService, paymentLogger);

        bankOrder.checkout( 1200, "customer@gmail.com");

        System.out.println();

        // CRYPTO

        PaymentProcessor cryptoPayment = new CryptoPayment();

        OrderService cryptoOrder = new OrderService( cryptoPayment, discountService, orderRepository, emailService, paymentLogger );

        cryptoOrder.checkout( 2000, "customer@gmail.com" );

        System.out.println();

        // REFUND

        Refundable refundable =  new CreditCardPayment();

        RefundService refundService =  new RefundService(refundable);

        refundService.refund(500);
    }
}

// PAYMENT PROCESSOR

interface PaymentProcessor { 
  void pay(double amount);
}

// REFUNDABLE

interface Refundable {
    void refund(double amount);
}

// CREDIT CARD PAYMENT

class CreditCardPayment 
  implements PaymentProcessor, Refundable {  
    @Override
    public void pay(double amount) {

        System.out.println( "Processing credit card payment: " + amount);
    }

    @Override
    public void refund(double amount) {

        System.out.println( "Credit card refund processed: "  + amount );
    }
}

// PAYPAL PAYMENT

class PayPalPayment
        implements PaymentProcessor, Refundable {
          
    @Override
    public void pay(double amount) {

        System.out.println( "Processing PayPal payment: "  + amount );
    }

    @Override
    public void refund(double amount) {

        System.out.println( "PayPal refund processed: "  + amount );
    }
}

// BANK TRANSFER PAYMENT

class BankTransferPayment
        implements PaymentProcessor {

    @Override
    public void pay(double amount) {

        System.out.println( "Processing bank transfer: "  + amount);
    }
}

// CRYPTO PAYMENT

class CryptoPayment
        implements PaymentProcessor {

    @Override
    public void pay(double amount) {

        System.out.println( "Processing crypto payment: "  + amount);
    }
}

// DISCOUNT SERVICE

class DiscountService {

    public double applyDiscount(double amount) {

        if (amount > 1000) {

            return amount * 0.9;
        }
        return amount;
    }
}

// ORDER REPOSITORY

class OrderRepository {

    public void save(double amount) {

        System.out.println("Saving order to database: "  + amount );
    }
}

// EMAIL SERVICE

class EmailService {

    public void sendConfirmation(String email) {

        System.out.println(
                "Sending confirmation email to " + email);
    }
}

// PAYMENT LOGGER

class PaymentLogger {

    public void log(String message) {

        System.out.println( "LOG: " + message );
    }
}

// ORDER SERVICE

class OrderService {

    private final PaymentProcessor paymentProcessor;
    private final DiscountService discountService;
    private final OrderRepository orderRepository;
    private final EmailService emailService;
    private final PaymentLogger paymentLogger;


    public OrderService(
            PaymentProcessor paymentProcessor, DiscountService discountService,  OrderRepository orderRepository,  EmailService emailService,PaymentLogger paymentLogger) {

        this.paymentProcessor = paymentProcessor;
        this.discountService = discountService;
        this.orderRepository = orderRepository;
        this.emailService = emailService;
        this.paymentLogger = paymentLogger;
    }

    public void checkout( double amount, String email) {

        // 1. Apply discount
        double finalAmount = discountService.applyDiscount(amount);
      
        // 2. Process payment
        paymentProcessor.pay(finalAmount);

        // 3. Save order
        orderRepository.save(finalAmount);

        // 4. Send confirmation email
        emailService.sendConfirmation(email);

        // 5. Log transaction
        paymentLogger.log( "Payment completed: "   + finalAmount );
    }
    }

// REFUND SERVICE

class RefundService {

    private final Refundable refundable;


    public RefundService(Refundable refundable) {

        this.refundable = refundable;
    }

    public void refund(double amount) {

        refundable.refund(amount);
    }
}
