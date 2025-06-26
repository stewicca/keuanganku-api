package com.wreccy.keuanganku.controller;

import org.springframework.http.*;
import com.wreccy.keuanganku.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.dao.DataIntegrityViolationException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseUtil.buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Something went wrong",
                null
        );
    }

    @ExceptionHandler({ ResponseStatusException.class })
    public ResponseEntity<?> handlingResponseStatusException(ResponseStatusException e) {
        return ResponseUtil.buildResponse(
                HttpStatus.valueOf(e.getStatusCode().value()),
                e.getReason(),
                null
        );
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<?> handlingConstraintViolationException(ConstraintViolationException e) {
        return ResponseUtil.buildResponse(
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                null
        );
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<?> handlingDataIntegrityViolationException(DataIntegrityViolationException e) {
        String message = "";
        HttpStatus status = HttpStatus.CONFLICT;

        if (e.getCause() != null) {
            String causeMessage = e.getCause().getMessage();
            if (causeMessage.contains("duplicate key value")) {
                message = "Data already exist.";
            } else if (causeMessage.contains("null value in column")) {
                message = "Data cannot be null.";
                status = HttpStatus.BAD_REQUEST;
            } else if (causeMessage.contains("violates check constraint")) {
                message = "Data must be appropriate.";
                status = HttpStatus.BAD_REQUEST;
            } else if (causeMessage.contains("violates foreign key constraint")) {
                message = "Data cannot be deleted because it is used by other data";
                status = HttpStatus.BAD_REQUEST;
            } else {
                message = "Unexpected error occurred";
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return ResponseUtil.buildResponse(
                status,
                message,
                null
        );
    }
}
