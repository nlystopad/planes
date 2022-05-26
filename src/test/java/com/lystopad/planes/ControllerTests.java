package com.lystopad.planes;

import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.repository.PlaneRepository;
import com.lystopad.planes.web.PlaneController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PlaneController.class)
public class ControllerTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    PlaneRepository repository;

    @Ignore
    @Test
    public void createPlaneSuccess() throws Exception {
        Plane plane = Plane.builder().name("F-18").build();

        Mockito.when(repository.save(plane)).thenReturn(plane);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(plane));

        mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("F-18")));
    }

    @Ignore
    @Test
    public void getPlaneByIdSuccess() throws Exception {
        Plane plane = Plane.builder().name("F-16").ammunition(5).build();

        Mockito.when(repository.findById(plane.getId())).thenReturn(Optional.of(plane));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/planes/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("F-16")));

    }

    @Ignore
    @Test
    public void getAllPlanesSuccess() throws Exception {
        Plane plane = Plane.builder().name("F-18").isFighter(true).build();

        List<Plane> records = new ArrayList<>(List.of(plane));

        repository.save(plane);

        Mockito.when(repository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders.get("/planes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[2].name", is("F-18")));
    }
}
