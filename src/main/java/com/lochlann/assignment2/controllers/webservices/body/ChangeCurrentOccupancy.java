package com.lochlann.assignment2.controllers.webservices.body;

import javax.validation.constraints.*;

// TODO - Max(maxOccupancy)
public record ChangeCurrentOccupancy(
    @NotNull
    @Min(0)
    int currentOccupancy
) {}
