package com.ufcg.atg.util;

/**
 * Contains static methods and constants of general utility.
 *
 * @author Vélmer Oliveira
 */
public final class Utils {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static final String STRING_EMPTY = "";

    /**
     * Private constructor to prevent instantiation.
     */
    private Utils() {}

    /**
     * Returns the string representation of a float. If the float hasn't a
     * decimal part, only the integer part will be returned.
     *
     * Ex: floatToString(3.14) == "3.14" // Float that has decimal part
     * Ex: floatToString(3.0) == "3" // Float that hasn't decimal part
     *
     * @param f Float number to have its string representation returned.
     * @return String representation returned of specified float number.
     */
    public static String floatToString(float f) {
        int intRepr = (int) f;
        boolean hasNoDecimalPart = f - intRepr == 0f;
        String floatToString = hasNoDecimalPart ? String.valueOf(intRepr) :
                String.valueOf(f);
        return floatToString;
    }

    /**
     * Reverses the specified string.
     *
     * @param s String to be reversed.
     * @return Reverses the specified string.
     */
    public static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

}
