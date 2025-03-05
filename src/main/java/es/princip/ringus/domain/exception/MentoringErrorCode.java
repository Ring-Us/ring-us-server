package es.princip.ringus.domain.exception;

import es.princip.ringus.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum MentoringErrorCode implements ErrorCode {

    MENTORING__NOT_FOUND(HttpStatus.NOT_FOUND,"해당 신청이 존재하지 않음"),
    MENTORING_TIME_CONVERT_ERROR(HttpStatus.NOT_MODIFIED,"신청 시간 변환 오류"),
    MENTORING_TIME_ERROR(HttpStatus.BAD_REQUEST,"신청 가능 시간 오류");

    MentoringErrorCode(HttpStatus status , String message) {
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
