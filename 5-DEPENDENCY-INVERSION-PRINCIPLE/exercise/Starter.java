// DIP Practice Exercise — starter code. OrderProcessor constructs its own
// MySQLDatabase, so swapping storage (or testing without a real DB) means
// editing OrderProcessor itself. See README.md in this folder for the full
// task and requirements.

class MySQLDatabase {
    public void save(String order) {
        System.out.println("Saving order to MySQL: " + order);
    }
}

class OrderProcessor {
    private MySQLDatabase database = new MySQLDatabase();

    public void process(String order) {
        System.out.println("Processing order: " + order);
        database.save(order);
    }
}

public class Starter {
    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();
        processor.process("Order #1001");

        // The team wants to switch to MongoDB in production, and use an
        // in-memory fake in tests — without ever touching OrderProcessor.
    }
}
