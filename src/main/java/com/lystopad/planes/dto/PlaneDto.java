package com.lystopad.planes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class PlaneDto {

    @NotNull(message = "Name must be filled")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of plane", example = "F-16", required = true)
    public String name;

    @Schema(description = "Quantity of plane ammunition")
    public int ammunition;

    @NotNull
    @Schema(description = "Flag which should be true if plane is fighter", required = true)
    public boolean isFighter;

    @Schema(description = "Date and time of plane creation", required = true, pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime creationDate;

    @Schema(description = "Quantity of crew of this plane")
    public int crewQuantity;

    @Schema(hidden = true)
    public PilotDto mainPilot;


}
