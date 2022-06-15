package com.lystopad.planes.utils.config;

import com.lystopad.planes.domain.Pilot;
import com.lystopad.planes.dto.PilotDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class PilotMapper extends CustomMapper<Pilot, PilotDto> {
    @Override
    public void mapAtoB(Pilot pilot, PilotDto pilotDto, MappingContext context) {
        super.mapAtoB(pilot, pilotDto, context);
    }
}
