package edu.icet.clothify.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidateUtil {
    private PasswordValidateUtil(){}

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isValid(String password) {
        if (password == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static String getRequirements() {
        return "Password must be at least 8 characters long, include an uppercase letter, " +
                "a lowercase letter, a number, and a special character (@#$%^&+=!).";
    }
}
