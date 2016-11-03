import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import tell.dont.ask.EmailAddress;
import tell.dont.ask.EmailService;
import tell.dont.ask.Patient;
import tell.dont.ask.TextMessageService;


public class PatientReminderTest {
    private static final EmailAddress EMAIL_ADDRESS = new EmailAddress("an@email.address");
    private static final String PHONE_NUMBER = "07950518195";

    private final Patient emailPatient = new Patient();
    private final Patient phonePatient = new Patient();

    private final EmailService emailService = mock(EmailService.class);
    private final TextMessageService phoneService = mock(TextMessageService.class);

    private final PatientReminder reminder = new PatientReminder(emailService, phoneService);
    
    @Before public void
    setup() {
        phonePatient.setPhoneNumber(PHONE_NUMBER);
        emailPatient.setEmailAddress(EMAIL_ADDRESS);
    }

    @Test public void
    a_patient_with_an_email_address_receives_an_email() {
        reminder.remind(emailPatient);
        verify(emailService).emailReminderTo(EMAIL_ADDRESS);
    }

    @Test public void
    a_patient_with_a_phone_number_receives_a_text_message() {
        reminder.remind(phonePatient);
        verify(phoneService).sendTextReminderTo(PHONE_NUMBER);
    }

    @Test public void
    a_patient_with_no_email_address_does_not_get_an_email() {
        reminder.remind(phonePatient);
        verify(emailService, never()).emailReminderTo(new EmailAddress(Mockito.anyString()));
    }

    @Test public void
    a_patient_with_no_phone_number_does_not_receive_a_text() {
        reminder.remind(emailPatient);
        verify(phoneService, never()).sendTextReminderTo(Mockito.anyString());
    }
    
    @Test public void
    a_patient_with_an_invalid_phone_number_does_not_receive_a_test() {
        Patient invalidPatient = new Patient();
        String invalidPhoneNumber = "This is not a phone number";
        invalidPatient.setPhoneNumber(invalidPhoneNumber);
        reminder.remind(invalidPatient);
        verify(phoneService, never()).sendTextReminderTo(Mockito.anyString());
    }

    @Test public void
    a_patient_with_a_home_phone_number_is_not_texted() {
        Patient homePhonePatient = new Patient();
        String homePhone = "01254987654";
        homePhonePatient.setPhoneNumber(homePhone);
        reminder.remind(homePhonePatient);
        verify(phoneService, never()).sendTextReminderTo(Mockito.anyString());
    }

    @Test public void
    a_patient_with_a_home_phone_is_rung() {
        Patient homePhonePatient = new Patient();
        String homePhone = "01254987654";
        homePhonePatient.setPhoneNumber(homePhone);
        reminder.remind(homePhonePatient);
        verify(phoneService).callWithReminder(Mockito.anyString());
    }
}
