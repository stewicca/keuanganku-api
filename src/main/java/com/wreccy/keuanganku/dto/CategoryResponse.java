package com.wreccy.keuanganku.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private String id;

    private String name;

    private String label;
}
