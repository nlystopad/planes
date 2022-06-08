package com.lystopad.planes.utils.config;

import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.dto.PlaneDeleteDto;
import com.lystopad.planes.dto.PlaneDto;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

@Component
public class PlaneConverter {
    private final MapperFacade mapperFacade;

    public PlaneConverter(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }

    public MapperFacade getMapperFacade() {
        return mapperFacade;
    }

    public PlaneDto toDto(Plane entity) {
        return mapperFacade.map(entity, PlaneDto.class);
    }


    public Plane fromDto(PlaneDto dto) {
        return mapperFacade.map(dto, Plane.class);
    }

    public PlaneDeleteDto toDeleteDto(Plane entity) {
        return mapperFacade.map(entity, PlaneDeleteDto.class);
    }
}
