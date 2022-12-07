package com.lochlann.assignment2.controllers;

import jakarta.validation.constraints.*;
import org.springframework.data.repository.query.Param;

// TODO - @Param("maxOccupancy)int maxOccupancy, @Max(maxOccupancy)int currentOccupancy ???
public record NewOffice (
        @NotNull
        @Min(1)
        @Max(10)
        int maxOccupancy,

        @NotNull
        int currentOccupancy,

        @NotNull
        int departmentId
) {}

