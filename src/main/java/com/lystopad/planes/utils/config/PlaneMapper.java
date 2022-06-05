package com.lystopad.planes.utils.config;

import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.dto.PlaneDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class PlaneMapper extends CustomMapper<Plane, PlaneDto> {

    @Override
    public void mapBtoA(PlaneDto dto, Plane plane, MappingContext context) {
        super.mapBtoA(dto, plane, context);
    }
}
