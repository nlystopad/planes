package com.lystopad.planes.utils.config;

import com.lystopad.planes.domain.Pilot;
import com.lystopad.planes.dto.PilotDto;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

@Component
public class PilotConverter {
    private final MapperFacade mapperFacade;

    public PilotConverter(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }

    public MapperFacade getMapperFacade() {
        return mapperFacade;
    }

    public PilotDto toDto(Pilot entity) {
        return mapperFacade.map(entity, PilotDto.class);
    }

    public Pilot fromDto(PilotDto entity) {
        return mapperFacade.map(entity, Pilot.class);
    }
}
