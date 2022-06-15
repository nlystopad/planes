package com.lystopad.planes.utils.config;

import com.lystopad.planes.domain.Pilot;
import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.dto.PilotDto;
import com.lystopad.planes.dto.PlaneDeleteDto;
import com.lystopad.planes.dto.PlaneDto;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

public class MappingConfig implements OrikaMapperFactoryConfigurer {
    @Override
    public void configure(MapperFactory orikaMapperFactory) {

        orikaMapperFactory.classMap(Plane.class, PlaneDto.class)
                .customize(new PlaneMapper())
                .byDefault()
                .register();

        orikaMapperFactory.classMap(Plane.class, PlaneDto.class)
                .byDefault()
                .register();

        orikaMapperFactory.classMap(Plane.class, PlaneDeleteDto.class)
                .byDefault()
                .register();

        orikaMapperFactory.classMap(Pilot.class, PilotDto.class)
                .byDefault()
                .register();
    }
}


