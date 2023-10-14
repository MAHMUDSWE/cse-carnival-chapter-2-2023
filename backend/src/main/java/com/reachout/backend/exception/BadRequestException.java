package com.reachout.backend.exception;

import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.AuthenticationResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private ApiResponse apiResponse;
    private AuthenticationResponse authenticationResponse;

    public BadRequestException(ApiResponse apiResponse) {
        super();
        this.apiResponse = apiResponse;
    }

    public BadRequestException(AuthenticationResponse authenticationResponse) {
        super();
        this.authenticationResponse = authenticationResponse;
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
