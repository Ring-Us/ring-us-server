package es.princip.ringus.global.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Slf4j
public class CustomRuntimeException extends RuntimeException{

    private HttpStatus status;
    private String message;
    private String code;

    public CustomRuntimeException(ErrorCode errorCode){
        super(errorCode.message());
        this.status = errorCode.status();
        this.message = errorCode.message();
        this.code = errorCode.code();

        log.error("CustomRuntimeException 발생: status={}, code={}, message={}", status, code, message, this);
    }

}
