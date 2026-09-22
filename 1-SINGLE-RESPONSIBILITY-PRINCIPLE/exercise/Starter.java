// SRP Practice Exercise — starter code. Refactor this so each responsibility
// (data, calculation, printing, saving, emailing) lives in its own class.
// See README.md in this folder for the full task and requirements.

class Invoice {
    private String customerName;
    private double[] itemPrices;

    public Invoice(String customerName, double[] itemPrices) {
        this.customerName = customerName;
        this.itemPrices = itemPrices;
    }

    public double calculateTotal() {
        double total = 0;
        for (double price : itemPrices) {
            total += price;
        }
        return total;
    }

    public void printInvoice() {
        System.out.println("Invoice for: " + customerName);
        for (double price : itemPrices) {
            System.out.println(" - $" + price);
        }
        System.out.println("Total: $" + calculateTotal());
    }

    public void saveToFile(String filename) {
        System.out.println("Saving invoice to " + filename);
    }

    public void emailInvoice(String emailAddress) {
        System.out.println("Emailing invoice to " + emailAddress);
    }
}

public class Starter {
    public static void main(String[] args) {
        Invoice invoice = new Invoice("Jane Doe", new double[] { 19.99, 42.50, 5.00 });
        invoice.printInvoice();
        invoice.saveToFile("invoice-001.txt");
        invoice.emailInvoice("jane@example.com");
    }
}
