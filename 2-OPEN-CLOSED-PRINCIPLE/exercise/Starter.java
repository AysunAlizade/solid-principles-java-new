// OCP Practice Exercise — starter code. Refactor this so adding a new
// customer tier never requires editing DiscountCalculator again.
// See README.md in this folder for the full task and requirements.

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

public class Starter {
    public static void main(String[] args) {
        DiscountCalculator calculator = new DiscountCalculator();
        double amount = 200.0;

        double regularDiscount = calculator.getDiscount("regular");
        double premiumDiscount = calculator.getDiscount("premium");
        double vipDiscount = calculator.getDiscount("vip");

        System.out.println("Regular pays: " + (amount - amount * regularDiscount));
        System.out.println("Premium pays: " + (amount - amount * premiumDiscount));
        System.out.println("VIP pays: " + (amount - amount * vipDiscount));

        // Marketing just asked for a "student" tier at 15% off.
        // Can you add it without touching DiscountCalculator?
    }
}
