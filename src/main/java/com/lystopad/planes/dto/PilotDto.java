package com.lystopad.planes.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PilotDto {

    @NotNull(message = "Field must not be null")
    @Schema(description = "First name of pilot", example = "Steve", required = true)
    @Size(min = 2, max = 32, message = "First name of pilot must be between 2 and 32 characters")
    public String firstName;

    @Schema(description = "Last name of pilot", example = "White", required = true)
    @Size(min = 2, max = 32, message = "Last name of pilot must be between 2 and 32 characters")
    @NotNull(message = "Field must not be null")
    public String lastName;

    @Schema(description = "Callsign of pilot", example = "Hawk", required = true)
    @NotNull(message = "Field must not be null")
    public String callsign;

    @Schema(description = "Age of pilot")
    public Integer age;


}
