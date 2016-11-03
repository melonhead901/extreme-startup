package tell.dont.ask;

/**
 * Created by rchew on 11/3/16.
 */
public class PhoneNumber {
    private final String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isValid() {
        return PhoneNumberUtil.isValidPhoneNumber(phoneNumber);
    }

    public boolean isMobile() {
        return PhoneNumberUtil.isMobileNumber(phoneNumber);
    }

    /**
     * Created by rchew on 11/3/16.
     */
    public static class PhoneNumberUtil {

        private static final String MOBILE_PHONE_PREFIX = "07";
        private static final int PHONE_NUMBER_FULL_LENGTH = 11;

        private static boolean isMobileNumber(String phoneNumber) {
            return phoneNumber.startsWith(MOBILE_PHONE_PREFIX);
        }

        private static boolean isValidPhoneNumber(String phoneNumber) {
            // Phone numbers are null when teh patient doesn't have one
            return phoneNumber != null
                    && phoneNumber.length() == PHONE_NUMBER_FULL_LENGTH;
        }
    }
}
