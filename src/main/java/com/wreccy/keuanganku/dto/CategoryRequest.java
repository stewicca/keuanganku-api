package com.wreccy.keuanganku.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    @NotNull
    @NotBlank
    private String name;
}
