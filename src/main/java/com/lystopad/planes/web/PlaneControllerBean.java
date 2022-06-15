package com.lystopad.planes.web;

import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.dto.PilotDto;
import com.lystopad.planes.dto.PlaneDeleteDto;
import com.lystopad.planes.dto.PlaneDto;
import com.lystopad.planes.service.PlaneService;
import com.lystopad.planes.utils.config.PilotConverter;
import com.lystopad.planes.utils.config.PlaneConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class PlaneControllerBean implements PlaneController {

    private final PlaneService planeService;

    private final PlaneConverter planeConverter;

    private final PilotConverter pilotConverter;

    @PostMapping("/planes")
    @Override
    public PlaneDto createPlane(@RequestBody @Valid PlaneDto planeForSave) {
        var plane = planeConverter.getMapperFacade().map(planeForSave, Plane.class);
        return planeConverter.toDto(planeService.create(plane));
    }

    @GetMapping("/planes")
    @Override
    public Collection<Plane> getAll() {
        return planeService.getAll();
    }

    @GetMapping("/planes/{id}")
    @Override
    public PlaneDto getById(@PathVariable Integer id) {
        log.debug("getById() Controller - start: id = {}", id);
        var plane = planeService.getById(id);
        log.debug("getById() Controller - to dto start: id = {}", id);
        var dto = planeConverter.toDto(plane);
        log.debug("getById() Controller - end: name = {}", dto.name);
        return dto;

    }

    @PutMapping("/planes/{id}")
    @Override
    public PlaneDto updateById(@RequestBody @Valid PlaneDto planeForUpdate, @PathVariable Integer id) {
        log.debug("updateById() Controller - start: id = {}", id);
        var plane = planeConverter.getMapperFacade().map(planeForUpdate, Plane.class);
        log.debug("updateById() Controller - end: id = {}", id);
        return planeConverter.toDto(planeService.updateById(plane, id));
    }

    @PatchMapping("/planes/{id}/delete")
    @Override
    public PlaneDeleteDto removeById(@PathVariable Integer id) {
        var planeToReturn = planeConverter.toDeleteDto(planeService.getById(id));
        planeService.removeById(id);
        return planeToReturn;
    }


    @GetMapping(value = "/planes", params = {"name"})
    @Override
    public PlaneDto findPlaneByName(String name) {
        log.debug("findPlaneByName() Controller - start: name = {}", name);
        var dto = planeConverter.toDto(planeService.findPlaneByName(name));
        log.debug("findPlaneByName() Controller - end: id = {}", planeService.findPlaneByName(name).getId());
        return dto;
    }

    @GetMapping(value = "/planes/fighters")
    @Override
    public Collection<Plane> findPlaneByFighter() {
        return planeService.findPlaneByFighter();
    }

    @PatchMapping(value = "/planes/{id}")
    @Override
    public PlaneDto updateDate(@RequestParam("datetime")
                               @DateTimeFormat() String ldc,
                               @PathVariable Integer id) {
        log.debug("updateDate() Controller - start: id = {}, datetime = {}", id, ldc);
        var localDateTime = LocalDateTime.parse(ldc, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        log.debug("updateDate() Controller - finished parsing of datetime: datetime = {}", localDateTime);
        planeService.updateDate(id, localDateTime);
        log.debug("updateDate() Controller - end: id = {}", id);
        return planeConverter.toDto(planeService.getById(id));
    }

    @PutMapping(value = "/planes/{id}/pilot")
    @Override
    public PlaneDto addMainPilot(@PathVariable Integer id, @RequestBody PilotDto mainPilot) {
        log.debug("addMainPilot() Controller - start: id = {}, pilot = {}", id, mainPilot);
        var pilot = pilotConverter.fromDto(mainPilot);
        var plane = planeService.addMainPilot(id, pilot);
        var dto = planeConverter.toDto(plane);
        log.debug("addMainPilot() Controller - end: id = {}, plane = {}", id, plane);
        return dto;
    }

    @GetMapping(value = "/pilots/{id}")
    @Override
    public PilotDto getPilotByPlaneId(@PathVariable Integer id) {
        log.debug("getPilotByPlaneId() Controller - start: id of plane = {}", id);
        var pilot = planeService.getPilotByPlaneId(id);
        log.debug("getPilotByPlaneId() Controller - got pilot = {}", pilot);
        var dto = pilotConverter.toDto(pilot);
        log.debug("getPilotByPlaneId() Controller - end : dto = {}", dto);
        return dto;
    }

}
