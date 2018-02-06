package com.mmhdev.devcv.core.utils;

/**
 */
public final class EmailValidator {
    public static boolean isValid(String email){
        return !StringUtils.isNullEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
