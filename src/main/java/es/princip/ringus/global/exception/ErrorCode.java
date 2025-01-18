package es.princip.ringus.global.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
        HttpStatus status();
        String message();
        String code();
}
