package tell.dont.ask;

public class Patient {

    private EmailAddress email;
    private String phone;
    private PhoneNumber phoneNumber;

    public void setEmailAddress(EmailAddress email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
        this.phoneNumber = new PhoneNumber(phoneNumber);
        
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
        // this.phoneNumber = new PhoneNumber(this.phone);
        if (this.phoneNumber != null) {
            if (this.phoneNumber.isValid()) {
                if (this.phoneNumber.isMobile()) {
                    this.phoneNumber.sendTextReminder(phoneService);
                } else {
                    this.phoneNumber.callWithReminder(phoneService);
                }
            }
        }
        // String phoneNumber = this.getPhoneNumber();

        // if(PhoneNumberUtil.isValidPhoneNumber(phoneNumber)) {
        //     if(PhoneNumberUtil.isMobileNumber(phoneNumber)) {
        //         phoneService.sendTextReminderTo(phoneNumber);
        //     } else {
        //         phoneService.callWithReminder(phoneNumber);
        //     }
        // }
    }
}
