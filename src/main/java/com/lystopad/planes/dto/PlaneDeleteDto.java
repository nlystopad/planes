package com.lystopad.planes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

public class PlaneDeleteDto {
    @JsonIgnore
    @NotNull
    public Integer id;

    public String message = String.format("Plane with id = %d was deleted successfully", id);

}
