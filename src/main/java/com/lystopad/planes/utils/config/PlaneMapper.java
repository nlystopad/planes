package com.lystopad.planes.utils.config;

import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.dto.PlaneDeleteDto;
import com.lystopad.planes.dto.PlaneDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlaneMapper {
    PlaneMapper INSTANCE  = Mappers.getMapper(PlaneMapper.class);

    PlaneDto planeToPlaneDto(Plane plane);
    Plane planeDtoToPlane(PlaneDto dto);
    PlaneDeleteDto planeToPlaneDeleteDto(Plane plane);
}
