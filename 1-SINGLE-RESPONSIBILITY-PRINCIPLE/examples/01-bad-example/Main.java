// ❌ BAD: one class owns validation, persistence, AND email delivery.
// It has three reasons to change: a new validation rule, a new database,
// or a new email provider all force an edit to this same class.
class UserService {

    public void createUser(String email) {

        // validation
        if (!email.contains("@")) {
            throw new RuntimeException("Invalid email");
        }

        // save to DB
        System.out.println("Saving user to DB");

        // send email
        System.out.println("Sending email");
    }
}

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.createUser("jane@example.com");
    }
}

/*
 * Problems with this design:
 *  - Multiple responsibilities crammed into one class (validation + persistence + notification)
 *  - Hard to test: a unit test for validation also drags in "DB" and "email" side effects
 *  - Hard to modify: changing how email is sent risks breaking validation or persistence
 *  - Hard to reuse: another feature that needs "just validation" still pulls in the whole class
 */
