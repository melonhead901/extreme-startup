import tell.dont.ask.EmailService;
import tell.dont.ask.Patient;
import tell.dont.ask.TextMessageService;


public class PatientReminder {

    private final EmailService emailService;
    private final TextMessageService phoneService;

    public PatientReminder(EmailService emailService, TextMessageService phoneService) {
        this.emailService = emailService;
        this.phoneService = phoneService;
    }
    
    public void remind(Patient patient) {
        patient.remindViaEmail(emailService);


        // Text a patient if they have a mobile, otherwise ring them
        patient.remindViaPhone(phoneService);
    }


}