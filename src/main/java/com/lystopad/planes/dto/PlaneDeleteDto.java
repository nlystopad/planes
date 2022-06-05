package com.lystopad.planes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PlaneDeleteDto {
    @JsonIgnore
    public Integer id;

    public String message = String.format("Plane with id = %d was deleted ",id);

}
