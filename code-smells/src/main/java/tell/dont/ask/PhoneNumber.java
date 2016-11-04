package tell.dont.ask;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by rchew on 11/3/16.
 */
public class PhoneNumber implements ContactMethod {

    private final String phoneNumber;
    private static final int PHONE_NUMBER_FULL_LENGTH = 11;
    private static final String MOBILE_PHONE_PREFIX = "07";

    public PhoneNumber(String phoneNumber) {
        checkNotNull(phoneNumber);
        assert(phoneNumber.length() == PHONE_NUMBER_FULL_LENGTH);
        this.phoneNumber = phoneNumber;
    }

    private boolean isMobile() {
        return phoneNumber.startsWith(MOBILE_PHONE_PREFIX);
    }

    private void sendTextReminder(TextMessageService phoneService) {
        phoneService.sendTextReminderTo(phoneNumber);
    }

    private void callWithReminder(TextMessageService phoneService) {
        phoneService.callWithReminder(phoneNumber);
    }

    @Override
    public void remind(ContactService contactService) {
        TextMessageService phoneService = (TextMessageService) contactService;
            if (isMobile()) {
                sendTextReminder(phoneService);
            } else {
                callWithReminder(phoneService);
            }
    }
}
