package com.lystopad.planes.web;

import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.service.PlaneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PlaneController {

    private final PlaneService planeService;

    @PostMapping("/planes")
    @ResponseStatus(HttpStatus.CREATED)
    public Plane createPlane(@RequestBody Plane plane) {
        return planeService.create(plane);
    }

    @GetMapping("/planes")
    @ResponseStatus(HttpStatus.OK)
    public List<Plane> getAll() {
        return planeService.getAll();
    }
    @GetMapping("/planes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Plane getById(@PathVariable Integer id){
        return planeService.getById(id);
    }
    @PutMapping("/planes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Plane updateById(@RequestBody Plane plane, @PathVariable Integer id){
        return planeService.updateById(plane, id);
    }
    @DeleteMapping("/planes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable Integer id){
        planeService.removeById(id);
    }
    @DeleteMapping("/planes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAll(){
        planeService.removeAll();
    }
}
