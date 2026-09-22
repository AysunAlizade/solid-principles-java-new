// ✅ GOOD: split the fat interface into small, focused ones.
// Each device implements only what it can genuinely support.
interface Printer {
    void print(String document);
}

interface Scanner {
    void scan(String document);
}

interface FaxMachine {
    void fax(String document);
}

class OldPrinter implements Printer {
    public void print(String document) {
        System.out.println("Printing: " + document);
    }
    // No scan()/fax() to fake — OldPrinter simply isn't a Scanner or FaxMachine.
}

class SmartOfficeMachine implements Printer, Scanner, FaxMachine {
    public void print(String document) {
        System.out.println("Printing: " + document);
    }

    public void scan(String document) {
        System.out.println("Scanning: " + document);
    }

    public void fax(String document) {
        System.out.println("Faxing: " + document);
    }
}

public class Main {
    static void printDocument(Printer printer, String doc) {
        printer.print(doc);
    }

    public static void main(String[] args) {
        printDocument(new OldPrinter(), "resume.pdf");
        printDocument(new SmartOfficeMachine(), "contract.pdf");

        Scanner scanner = new SmartOfficeMachine();
        scanner.scan("contract.pdf");
        // Scanner brokenScanner = new OldPrinter(); // ❌ won't compile — OldPrinter isn't a Scanner
    }
}

/*
 * Why this is better:
 *  - No device is forced to implement a method that doesn't apply to it
 *  - Interfaces describe real capabilities: Printer, Scanner, FaxMachine — nothing more
 *  - Adding a Stapler capability later only affects devices that actually staple
 */
