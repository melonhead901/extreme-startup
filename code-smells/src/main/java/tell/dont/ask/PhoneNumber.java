package tell.dont.ask;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by rchew on 11/3/16.
 */
public class PhoneNumber {

    private final String phoneNumber;
    private static final int PHONE_NUMBER_FULL_LENGTH = 11;

    public PhoneNumber(String phoneNumber) {
        checkNotNull(phoneNumber);
        assert(phoneNumber.length() == PHONE_NUMBER_FULL_LENGTH);
        this.phoneNumber = phoneNumber;
    }

    private boolean isMobile() {
        return PhoneNumberUtil.isMobileNumber(phoneNumber);
    }

    private void sendTextReminder(TextMessageService phoneService) {
        phoneService.sendTextReminderTo(phoneNumber);
    }

    private void callWithReminder(TextMessageService phoneService) {
        phoneService.callWithReminder(phoneNumber);
    }

    public void remind(TextMessageService phoneService) {
            if (isMobile()) {
                sendTextReminder(phoneService);
            } else {
                callWithReminder(phoneService);
            }
    }
}
