package com.wreccy.keuanganku.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequest {
    @NotNull
    @NotBlank
    private String category;

    private String description;

    @NotNull
    private Integer amount;
}
