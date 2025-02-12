package es.princip.ringus.domain.exception;

import es.princip.ringus.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum SignUpErrorCode implements ErrorCode {

    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND , "회원 존재하지 않음"),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 가입된 이메일"),
    WRONG_EMAIL(HttpStatus.BAD_REQUEST, "잘못된 이메일 주소"),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "잘못된 비밀번호"),
    SERVICE_TERM_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 서비스 약관"),
    NOT_AGREED_REQUIRED_SERVICE_TERM(HttpStatus.CONFLICT, "필수 서비스 약관 미동의"),
    ALREADY_REGISTERED_AS_MENTOR(HttpStatus.CONFLICT, "이미 멘토로 등록된 사용자"),
    ALREADY_REGISTERED_AS_MENTEE(HttpStatus.CONFLICT, "이미 멘티로 등록된 사용자");

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