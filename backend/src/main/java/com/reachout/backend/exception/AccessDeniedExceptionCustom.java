package com.reachout.backend.exception;

import com.reachout.backend.payload.SimpleResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessDeniedExceptionCustom extends RuntimeException {
    private SimpleResponse simpleResponse;

    private String message;

    public AccessDeniedExceptionCustom(SimpleResponse simpleResponse) {
        super();
        this.simpleResponse = simpleResponse;
    }

    public AccessDeniedExceptionCustom(String message) {
        super(message);
        this.message = message;
    }
}
