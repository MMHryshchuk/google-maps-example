package com.mmhdev.devcv.core.utils;

/**
 */
public final class StringUtils {
    public static boolean isNullEmpty(String text){
        return text == null || text.isEmpty();
    }

    public static String generateShortName(String firstName, String lastName){
        StringBuilder builder = new StringBuilder();
        if (!isNullEmpty(firstName)){
            builder.append(firstName.substring(0,1));
        }

        if (!isNullEmpty(firstName)){
            builder.append(lastName.substring(0,1));
        }

        return builder.toString();
    }
}
