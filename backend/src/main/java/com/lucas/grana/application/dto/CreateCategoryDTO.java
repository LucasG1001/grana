package com.lucas.grana.application.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryDTO {

    private String id;
    
    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(max = 7)
    private String color;

    @NotNull
    @Size(max = 30)
    private String icon;
}
