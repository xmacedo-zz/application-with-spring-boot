package io.xmacedo.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Exception extends RuntimeException {

    private static final long serialVersionUID = 4097507930259242070L;

    private final ExceptionEnum error;

    public Exception(ExceptionEnum error) {
        this.error = error;
    }
}
