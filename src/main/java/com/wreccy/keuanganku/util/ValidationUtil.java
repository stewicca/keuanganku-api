package com.wreccy.keuanganku.util;

import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@RequiredArgsConstructor
@Component
public class ValidationUtil {
    private final Validator validator;

    public void validate(Object object) {
        Set<ConstraintViolation<Object>> errors = validator.validate(object);
        if (!errors.isEmpty()) {
            throw new ConstraintViolationException(errors);
        }
    }
}
