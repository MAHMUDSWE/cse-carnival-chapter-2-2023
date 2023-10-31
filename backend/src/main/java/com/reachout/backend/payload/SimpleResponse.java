package com.reachout.backend.payload;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
public class SimpleResponse {
    int statusCode;
    String message;
}
