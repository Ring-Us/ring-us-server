package es.princip.ringus.domain.exception;

import es.princip.ringus.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum SignUpErrorCode implements ErrorCode {

    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND , "회원 존재하지 않음"),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 가입된 이메일"),
    WRONG_EMAIL(HttpStatus.BAD_REQUEST, "잘못된 이메일 주소"),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "잘못된 비밀번호");

    SignUpErrorCode(HttpStatus status, String message) {
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