package io.joinpa.joinpa.managers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FormValidator {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private static final String USERNAME_PATTERN = "";

    private static Pattern pattern;
    private static Matcher matcher;


    public static boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validateUsername(String username) {
//        pattern = Pattern.compile(USERNAME_PATTERN);
//        matcher = pattern.matcher(username);
//        return matcher.matches() && username.length() > 4;
        return true;
    }

    public static boolean validatePassword(String p1, String p2) {
        return p1.equals(p2) && p1.length() > 6;
    }
}
