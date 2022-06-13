package com.lystopad.planes;

import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.repository.PlaneRepository;
import com.lystopad.planes.service.PlaneServiceBean;
import com.lystopad.planes.utils.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {
    @Mock
    private PlaneRepository repository;
    @InjectMocks
    private PlaneServiceBean service;

    @Test
    public void whenSavePlaneShouldReturnPlane() {
        Plane plane = new Plane();
        plane.setName("F-16");


        when(repository.save(ArgumentMatchers.any(Plane.class))).thenReturn(plane);

        Plane created = service.create(plane);

        assertThat(created.getName()).isSameAs(plane.getName());
        verify(repository).save(plane);
    }

    @Test
    public void whenGivenIdShouldReturnPlaneIfFound(){
        Plane plane = new Plane();
        plane.setId(70);

        when(repository.findById(plane.getId())).thenReturn(Optional.of(plane));

        Plane expected = service.getById(plane.getId());

        assertThat(expected).isSameAs(plane);
        verify(repository, times(2)).findById(plane.getId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowExceptionWhenPlaneDoesntExist(){
        Plane plane = new Plane();
        plane.setId(25);
        plane.setName("F-12");

        given(repository.findById(anyInt())).willReturn(Optional.empty());
        service.getById(plane.getId());
    }
}
