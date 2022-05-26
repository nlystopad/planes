package com.lystopad.planes.web;

import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.service.PlaneService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class PlaneController {

    private final PlaneService planeService;

    @PostMapping("/planes")
    @ResponseStatus(HttpStatus.CREATED)
    public Plane createPlane(@RequestBody Plane plane) {
        return planeService.create(plane);
    }

    @GetMapping("/planes")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Plane> getAll() {
        return planeService.getAll();
    }

    @GetMapping("/planes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Plane getById(@PathVariable Integer id) {
        log.debug("getById() Controller - start: id = {}", id);
        Plane plane = planeService.getById(id);
        log.debug("getById() Controller - end: name = {}", plane.getName());
        return plane;

    }

    @PutMapping("/planes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Plane updateById(@RequestBody Plane plane, @PathVariable Integer id) {
        return planeService.updateById(plane, id);
    }

    @PatchMapping("/planes/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable Integer id) {
        planeService.removeById(id);
    }

    @DeleteMapping("/planes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAll() {
        planeService.removeAll();
    }

    @GetMapping(value = "/planes", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public Plane findPlaneByName(String name) {
        return planeService.findPlaneByName(name);
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
        LocalDateTime localDateTime = LocalDateTime.parse(ldc, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        planeService.updateDate(id, localDateTime);
    }


}
