package es.princip.ringus.domain.exception;

import es.princip.ringus.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum EmailErrorCode implements ErrorCode {

    SUCESSS_VALILDATION(HttpStatus.OK, "성공적으로 인증되었습니다"),
    TTL_EXPIRED(HttpStatus.FORBIDDEN, "TTL 만료"),
    ERROR_EXCEEDED_ATTEMPTS(HttpStatus.FORBIDDEN, "인증번호 틀림 횟수 5회 이상, 새로운 인증번호를 발급 받아주세요"),
    ERROR_INVALID_CODE(HttpStatus.BAD_REQUEST, "인증번호가 틀립니다");

    EmailErrorCode(HttpStatus status, String message) {
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