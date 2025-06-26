package com.wreccy.keuanganku.controller;

import org.springframework.http.*;
import com.wreccy.keuanganku.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<?> handleException(Exception error) {
        return ResponseUtil.buildResponse(
                HttpStatus.BAD_REQUEST,
                error.getMessage(),
                null
        );
    }

    @ExceptionHandler({ ResponseStatusException.class })
    public ResponseEntity<?> handlingResponseStatusException(ResponseStatusException error) {
        return ResponseUtil.buildResponse(
                HttpStatus.valueOf(error.getStatusCode().value()),
                error.getReason(),
                null
        );
    }
}
