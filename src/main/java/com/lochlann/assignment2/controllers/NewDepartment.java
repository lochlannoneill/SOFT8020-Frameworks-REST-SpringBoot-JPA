package com.lochlann.assignment2.controllers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewDepartment (
        @NotEmpty
        @NotBlank
        @Size(min=1, max=64)
        String name,

        @NotEmpty
        @NotBlank
        String email
) {}
