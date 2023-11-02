package com.reachout.backend.controllers;

import com.reachout.backend.exception.*;
import com.reachout.backend.payload.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.module.ResolutionException;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/protected")
    public String testProtected() {
        return "ok!";
    }

    @GetMapping("/public")
    public String testPublic() {
        return "ok!";
    }

    @GetMapping("/public/access-denied-exception")
    public String testException1() {
        SimpleResponse simpleResponse = new SimpleResponse(403, "Access Denied BRO!");
        throw new AccessDeniedExceptionCustom(simpleResponse);
    }
    @GetMapping("/public/bad-request-exception")
    public String testException2() {
        SimpleResponse simpleResponse = new SimpleResponse(400, "Bad Request BRO!");
        throw new BadRequestException(simpleResponse);
    }

    @GetMapping("/public/unauthorized-exception")
    public String testException3() {
        SimpleResponse simpleResponse = new SimpleResponse(401, "Unauthorized BRO!");
        throw new UnauthorizedException(simpleResponse);
    }

    @GetMapping("/public/api-exception-1")
    public String testException4() {
        throw new ApiException(HttpStatus.valueOf(401), "Api exception: Unauthorized BRO!");
    }

    @GetMapping("/public/api-exception-2")
    public String testException5() {
        throw new ApiException(HttpStatus.valueOf(400), "Api exception: Bad Request BRO!");
    }

    @GetMapping("/public/resource-not-found-exception")
    public String testException6() {
        throw new ResourceNotFoundException("Resource Not Found Bro!");
    }

    @GetMapping("/public/server-exception")
    public String testException7() {
        throw new ServerException("Internal Server Error Bro!");
    }
}
