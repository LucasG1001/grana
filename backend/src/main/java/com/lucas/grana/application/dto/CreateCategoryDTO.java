package com.lucas.grana.application.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryDTO {

    @NotNull
    @Size(max = 30)
    private String name;
}
