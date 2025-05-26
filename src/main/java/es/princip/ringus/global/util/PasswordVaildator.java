package es.princip.ringus.global.util;

import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.global.exception.CustomRuntimeException;
import java.util.regex.Pattern;

public class PasswordVaildator {
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*_+=/])[A-Za-z\\d!@#$%^&*_+=/]{8,20}$";

    public static void validate(String password) {
        if (!Pattern.matches(PASSWORD_PATTERN, password)) {
            throw new CustomRuntimeException(MemberErrorCode.INVAILD_PASSWORD);
        }
    }
}