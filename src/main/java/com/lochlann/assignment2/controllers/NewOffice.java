package com.lochlann.assignment2.controllers;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

