package com.wreccy.keuanganku.util;

import org.springframework.http.*;
import com.wreccy.keuanganku.dto.BodyResponse;

public class ResponseUtil {
    public static <T> ResponseEntity<?> buildResponse(HttpStatus status, String message, T data) {
        BodyResponse<T> body = BodyResponse.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();
        return ResponseEntity.status(status).body(body);
    }
}
