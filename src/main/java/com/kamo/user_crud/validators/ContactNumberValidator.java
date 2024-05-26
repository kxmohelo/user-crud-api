package com.kamo.user_crud.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The ContactNumberValidator class provides a method to validate contact numbers
 * based on a specific regex pattern.
 */
public class ContactNumberValidator {

    // Regular expression pattern for validating contact numbers
    private static final String CONTACT_NUMBER_PATTERN = "^\\+?\s?(?:[0-9]\s?){6,14}[0-9]$";

    // Compiled pattern from the regex
    private static final Pattern pattern = Pattern.compile(CONTACT_NUMBER_PATTERN);

    /**
     * Validates the given contact number format.
     *
     * @param contactNumber The contact number to be validated
     * @return True if the contact number is valid, false otherwise
     */
    public static boolean isValid(String contactNumber) {
        // Remove all whitespaces from the contact number
        contactNumber = contactNumber.replaceAll("[\s\\(\\)-]", "");
        // Match the contact number against the pattern
        Matcher matcher = pattern.matcher(contactNumber);
        // Return whether the contact number matches the pattern
        return matcher.matches();
    }
}
