package tell.dont.ask;

/**
 * Created by rchew on 11/3/16.
 */
public class PhoneNumberUtil {

    private static final String MOBILE_PHONE_PREFIX = "07";
    private static final int PHONE_NUMBER_FULL_LENGTH = 11;

    protected static boolean isMobileNumber(String phoneNumber) {
        return phoneNumber.startsWith(MOBILE_PHONE_PREFIX);
    }

    protected static boolean isValidPhoneNumber(String phoneNumber) {
        // Phone numbers are null when teh patient doesn't have one
        return phoneNumber != null
                && phoneNumber.length() == PHONE_NUMBER_FULL_LENGTH;
    }
}
