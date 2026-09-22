// ✅ GOOD: both Notification (high-level) and the concrete senders
// (low-level) depend on the same abstraction: MessageService.
interface MessageService {
    void send();
}

class EmailService implements MessageService {
    public void send() {
        System.out.println("Sending email");
    }
}

class SMSService implements MessageService {
    public void send() {
        System.out.println("Sending SMS");
    }
}

class PushService implements MessageService {
    public void send() {
        System.out.println("Sending a push notification to the mobile app");
    }
}

// Notification no longer knows (or cares) HOW the message is delivered —
// the concrete sender is injected from the outside.
class Notification {
    private final MessageService service;

    public Notification(MessageService service) {
        this.service = service;
    }

    public void notifyUser() {
        service.send();
    }
}

public class Main {
    public static void main(String[] args) {
        Notification byEmail = new Notification(new EmailService());
        byEmail.notifyUser();

        Notification byPush = new Notification(new PushService());
        byPush.notifyUser();

        // A unit test could inject a fake MessageService — no real email/SMS needed.
    }
}

/*
 * Why this is better:
 *  - Notification depends on an interface, not a concrete class → easy to test/mock
 *  - Adding a new channel (Push, Slack, etc.) needs zero changes to Notification
 *  - This is exactly what frameworks like Spring's dependency injection automate
 */
