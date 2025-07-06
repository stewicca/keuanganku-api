package com.wreccy.keuanganku.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetSpentAmountRequest {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private Integer year;

    @NotNull
    private Integer month;

    @NotNull
    private Integer spentAmount;
}
