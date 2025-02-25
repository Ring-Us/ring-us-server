package es.princip.ringus.domain.exception;

import es.princip.ringus.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum MenteeErrorCode implements ErrorCode {
    MENTEE_PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "멘티 프로필을 등록한 적이 없음");

    MenteeErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus status() {
        return this.status;
    }

    @Override
    public String message() {
        return this.message;
    }

    @Override
    public String code() {
        return this.name();
    }
}