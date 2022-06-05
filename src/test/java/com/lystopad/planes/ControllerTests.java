package com.lystopad.planes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.dto.PlaneDto;
import com.lystopad.planes.utils.config.PlaneMapper;
import com.lystopad.planes.web.PlaneController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlanesApplication.class)
@AutoConfigureMockMvc
public class ControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    private PlaneController controller;



    @Test
    public void createPlaneSuccess() throws Exception {
        Plane plane = Plane.builder().name("F-18").isFighter(true).creationDate(LocalDateTime.now()).ammunition(5).build();
        PlaneDto dto = PlaneMapper.INSTANCE.planeToPlaneDto(plane);
        Mockito.when(controller.createPlane(dto)).thenReturn(dto);

        MockHttpServletRequestBuilder mockRequest = post("/api/planes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(dto));

        mockMvc.perform(mockRequest)
                .andExpect(status().is(201));
    }

    @Test
    public void getPlaneByIdSuccess() throws Exception {
        Plane plane = Plane.builder().name("F-16").ammunition(5).build();
        PlaneDto dto = PlaneMapper.INSTANCE.planeToPlaneDto(plane);

        Mockito.when(controller.getById(plane.getId())).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/planes/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getAllPlanesSuccess() throws Exception {
        Plane plane = Plane.builder().name("F-18").isFighter(true).build();
        PlaneDto dto = PlaneMapper.INSTANCE.planeToPlaneDto(plane);

        Collection<Plane> records = new ArrayList<>(List.of(plane));

        controller.createPlane(dto);

        Mockito.when(controller.getAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/planes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("F-18")));
    }
}
