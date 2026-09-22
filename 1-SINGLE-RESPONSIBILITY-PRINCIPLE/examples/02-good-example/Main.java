// ✅ GOOD: each class has exactly one reason to change.
class UserValidator {
    public void validate(String email) {
        if (!email.contains("@")) {
            throw new RuntimeException("Invalid email");
        }
    }
}

class UserRepository {
    public void save(String email) {
        System.out.println("Saving user to DB");
    }
}

class EmailService {
    public void sendEmail(String email) {
        System.out.println("Sending email");
    }
}

class SMSService {
    public void sendSMS(String number) {
        System.out.println("Sending SMS");
    }
}

// UserService now only ORCHESTRATES the steps of "creating a user" —
// it delegates the actual work to single-purpose collaborators.
class UserService {
    private final UserValidator validator = new UserValidator();
    private final UserRepository repository = new UserRepository();
    private final EmailService emailService = new EmailService();
    private final SMSService smsService = new SMSService();

    public void createUser(String email) {
        validator.validate(email);
        repository.save(email);
        emailService.sendEmail(email);
    }
}

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.createUser("jane@example.com");
    }
}

/*
 * Why this is better:
 *  - UserValidator changes only when validation rules change
 *  - UserRepository changes only when the storage mechanism changes
 *  - EmailService / SMSService change only when the notification channel changes
 *  - UserService changes only when the *steps of the workflow* change
 *  - Each class can be unit-tested in isolation, and mocked when testing UserService
 */
