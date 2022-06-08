package com.lystopad.planes.web;

import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.dto.PlaneDeleteDto;
import com.lystopad.planes.dto.PlaneDto;
import com.lystopad.planes.service.PlaneService;
import com.lystopad.planes.utils.config.PlaneConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
public class PlaneController {

    private final PlaneService planeService;

    private final PlaneConverter converter;

    @PostMapping("/planes")
    @ResponseStatus(HttpStatus.CREATED)
    public PlaneDto createPlane(@RequestBody @Valid PlaneDto planeForSave) {
        var plane = converter.getMapperFacade().map(planeForSave, Plane.class);
        return converter.toDto(planeService.create(plane));
    }

    @GetMapping("/planes")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Plane> getAll() {
        return planeService.getAll();
    }

    @GetMapping("/planes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlaneDto getById(@PathVariable Integer id) {
        log.debug("getById() Controller - start: id = {}", id);
        var plane = planeService.getById(id);
        log.debug("getById() Controller - to dto start: id = {}", id);
        var dto = converter.toDto(plane);
        log.debug("getById() Controller - end: name = {}", dto.name);
        return dto;

    }

    @PutMapping("/planes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlaneDto updateById(@RequestBody @Valid PlaneDto planeForUpdate, @PathVariable Integer id) {
        log.debug("updateById() Controller - start: id = {}", id);
        var plane = converter.getMapperFacade().map(planeForUpdate, Plane.class);
        log.debug("updateById() Controller - end: id = {}", id);
        return converter.toDto(planeService.updateById(plane, id));
    }

    @PatchMapping("/planes/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public PlaneDeleteDto removeById(@PathVariable Integer id) {
        var planeToReturn = converter.toDeleteDto(planeService.getById(id));
        planeService.removeById(id);
        return planeToReturn;
    }


    @GetMapping(value = "/planes", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public PlaneDto findPlaneByName(String name) {
        log.debug("findPlaneByName() Controller - start: name = {}", name);
        var dto = converter.toDto(planeService.findPlaneByName(name));
        log.debug("findPlaneByName() Controller - end: id = {}", planeService.findPlaneByName(name).getId());
        return dto;
    }

    @GetMapping(value = "/planes/fighters")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Plane> findPlaneByFighter() {
        return planeService.findPlaneByFighter();
    }

    @PatchMapping(value = "/planes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDate(@RequestParam("datetime")
                           @DateTimeFormat() String ldc,
                           @PathVariable Integer id) {
        var localDateTime = LocalDateTime.parse(ldc, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        planeService.updateDate(id, localDateTime);
    }


}
