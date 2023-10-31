package com.reachout.backend.exception;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.reachout.backend.payload.SimpleResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
@Getter
@Setter
public class UnauthorizedException extends RuntimeException {
    private SimpleResponse simpleResponse;
    private String message;

    public UnauthorizedException(SimpleResponse simpleResponse) {
        super();
        this.simpleResponse = simpleResponse;
    }

    public UnauthorizedException(String message) {
        super(message);
        this.message = message;
    }


}
