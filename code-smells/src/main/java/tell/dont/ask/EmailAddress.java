package tell.dont.ask;

/**
 * Created by rchew on 11/3/16.
 */
public class EmailAddress {
    private final String email;

    public EmailAddress(String email) {
        this.email = email;
    }

    public void remind(EmailService emailService) {
        emailService.emailReminderTo(email);
    }
}
