package com.lystopad.planes.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class PlaneDeleteDto {
    @Schema(description = "This is message that should be shown when user deletes plane")
    public String message = "Plane with this id was deleted successfully";

}
