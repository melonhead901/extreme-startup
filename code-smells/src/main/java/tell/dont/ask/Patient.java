package tell.dont.ask;

public class Patient {

    private EmailAddress email;
    private String phoneNumber;

    public void setEmailAddress(EmailAddress email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void remindViaEmail(EmailService emailService) {
        if (this.hasEmailAddress()) {
            email.remind(emailService);
        }
    }

    private boolean hasEmailAddress() {
        return (this.email != null);
    }

    public void remindViaPhone(TextMessageService phoneService) {
        String phoneNumber = this.getPhoneNumber();

        if(PhoneNumberUtil.isValidPhoneNumber(phoneNumber)) {
            if(PhoneNumberUtil.isMobileNumber(phoneNumber)) {
                phoneService.sendTextReminderTo(phoneNumber);
            } else {
                phoneService.callWithReminder(phoneNumber);
            }
        }
    }
}
