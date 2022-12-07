package com.lochlann.assignment2.controllers;

import jakarta.validation.constraints.Min;

public record ChangeDepartment(
    @Min(1)
    int departmentId
) {}
