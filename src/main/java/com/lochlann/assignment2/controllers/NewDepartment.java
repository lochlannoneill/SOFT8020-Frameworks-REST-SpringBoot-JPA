package com.lochlann.assignment2.controllers;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public record NewDepartment (
        @NotEmpty
        @NotBlank
        @Size(min=1, max=64)
        String name,

        @NotEmpty
        @NotBlank
        String email
) {}
