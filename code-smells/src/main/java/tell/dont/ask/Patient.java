package tell.dont.ask;

public class Patient {

    private String email;
    private String phoneNumber;

    public void setEmailAddress(String email) {
        this.email = email;
    }

    public String getEmailAddress() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void remindViaEmail(EmailService emailService) {
        if (this.hasEmailAddress()) {
            emailService.emailReminderTo(email);
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
