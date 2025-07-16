package com.lucas.grana.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponseDTO {
    private String name;
    private String color;
}
