package com.lucas.grana.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponseDTO {
    private String id;
    private String name;
    private String color;
    private String icon;
}
