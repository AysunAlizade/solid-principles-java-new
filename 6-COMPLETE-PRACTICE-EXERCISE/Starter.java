class OrderService {

    void checkout(String paymentType, double amount, String email) {

        // calculate discount
        if(amount > 1000) {
            amount = amount * 0.9;
        }

        // process payment
        if(paymentType.equals("credit")) {
            System.out.println("Processing credit card");
        } else if(paymentType.equals("paypal")) {
            System.out.println("Processing PayPal");
        } else if(paymentType.equals("bank")) {
            System.out.println("Processing bank transfer");
        }

        // save order
        System.out.println("Saving order to database");

        // send email
        System.out.println("Sending email to " + email);

        // logging
        System.out.println("Logging transaction");
    }

    void refund(String paymentType, double amount) {
        if(paymentType.equals("credit") || paymentType.equals("paypal")) {
            System.out.println("Refund processed");
        } else {
            throw new RuntimeException("Refund not supported");
        }
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println("Try Code Star");
    }
}