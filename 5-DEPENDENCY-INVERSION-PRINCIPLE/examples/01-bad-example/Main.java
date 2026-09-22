// ❌ BAD: Notification (a high-level policy class) reaches down and
// constructs concrete low-level classes itself — it is welded to them.
class EmailService {
    public void send() {
        System.out.println("Sending email");
    }
}

class SmsService {
    public void send() {
        System.out.println("Sending sms");
    }
}

class Notification {
    // "new" inside a high-level class is the DIP smell to watch for.
    private final EmailService emailService = new EmailService();
    private final SmsService smsService = new SmsService();

    public void notifyUser() {
        emailService.send();
        // To notify by SMS instead, you'd have to edit this class's source.
    }
}

public class Main {
    public static void main(String[] args) {
        Notification notification = new Notification();
        notification.notifyUser();
    }
}

/*
 * Problems with this design:
 *  - Notification cannot be tested without a real EmailService running
 *  - Switching channels (or adding push notifications) means editing Notification
 *  - The high-level policy ("notify the user") is tangled with low-level detail
 *    ("how an email is sent")
 */
