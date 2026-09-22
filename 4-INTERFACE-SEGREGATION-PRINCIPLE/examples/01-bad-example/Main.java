// ❌ BAD: one "fat" interface forces every implementer to deal with
// methods that make no sense for them.
interface MultiFunctionDevice {
    void print(String document);
    void scan(String document);
    void fax(String document);
}

class OldPrinter implements MultiFunctionDevice {
    public void print(String document) {
        System.out.println("Printing: " + document);
    }

    // An OldPrinter can't scan or fax — but the interface demands the methods anyway.
    public void scan(String document) {
        throw new UnsupportedOperationException("This printer can't scan");
    }

    public void fax(String document) {
        throw new UnsupportedOperationException("This printer can't fax");
    }
}

public class Main {
    public static void main(String[] args) {
        MultiFunctionDevice printer = new OldPrinter();
        printer.print("resume.pdf");
        printer.scan("resume.pdf"); // 💥 throws at runtime
    }
}

/*
 * Problems with this design:
 *  - OldPrinter is forced to implement (and either fake or blow up on) scan() and fax()
 *  - Callers holding a MultiFunctionDevice reference can't know which methods are "real"
 *  - Any change to MultiFunctionDevice (e.g. adding staple()) ripples into every
 *    implementer, even ones that have nothing to do with stapling
 */
