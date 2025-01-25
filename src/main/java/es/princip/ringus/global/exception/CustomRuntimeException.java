package es.princip.ringus.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomRuntimeException extends RuntimeException{

    private HttpStatus status;
    private String message;
    private String code;

    public CustomRuntimeException(ErrorCode errorCode){
        super(errorCode.message());
        this.status = errorCode.status();
        this.message = errorCode.message();
        this.code = errorCode.code();
    }

}
