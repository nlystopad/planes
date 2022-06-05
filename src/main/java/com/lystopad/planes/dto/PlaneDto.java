package com.lystopad.planes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import java.time.LocalDateTime;

public class PlaneDto {

    @NotNull(message = "Name must be filled")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    public String name;

    public int ammunition;

    @NotNull(message = "field isFighter must be filled")
    public boolean isFighter;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime creationDate;

    public int crewQuantity;


}
