package com.wreccy.keuanganku.controller;

import org.springframework.http.*;
import lombok.RequiredArgsConstructor;
import com.wreccy.keuanganku.util.ResponseUtil;
import com.wreccy.keuanganku.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.wreccy.keuanganku.dto.UserMonthlySalaryRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/users")
public class UserController {
    private final UserService userService;

    @PatchMapping("/monthly-salary")
    public ResponseEntity<?> updateMonthlySalary(@RequestBody UserMonthlySalaryRequest request) {
        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                "Update monthly salary successful",
                userService.updateMonthlySalary(request)
        );
    }
}
