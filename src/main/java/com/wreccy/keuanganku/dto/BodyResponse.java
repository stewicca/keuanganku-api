package com.wreccy.keuanganku.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BodyResponse<T> {
    private Integer status;

    private String message;

    private T data;
}
