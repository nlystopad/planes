package com.lystopad.planes.web;

import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.dto.PlaneDeleteDto;
import com.lystopad.planes.dto.PlaneDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

public interface PlaneController {


    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This is endpoint to add a new plane.", description = "Create request to add a new plane.", tags = {"Plane"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new plane is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified plane request not found."),
            @ApiResponse(responseCode = "409", description = "Plane already exists")})
    PlaneDto createPlane(PlaneDto planeForSave);

    @Operation(summary = "This is endpoint to get all existing planes.", description = "Read request to get all planes which exist in database", tags = {"Plane"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Here are your planes."),
            @ApiResponse(responseCode = "204", description = "Whoops, looks like there are no planes in database"),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
            @ApiResponse(responseCode = "403", description = "Sorry, you don't have enough access rights to get all planes."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified plane request not found.")
    })
    @ResponseStatus(HttpStatus.OK)
    Collection<Plane> getAll();

    @Operation(summary = "This is endpoint to get 1 existing plane by its id.", description = "Read request to get 1 plane by id", tags = {"PlaneDto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Here is your plane."),
            @ApiResponse(responseCode = "404", description = "Sorry, there are no planes with such id in database"),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
    })
    @ResponseStatus(HttpStatus.OK)
    PlaneDto getById(Integer id);

    @Operation(summary = "This is endpoint to update 1 existing plane by its id.", description = "Update request to change all fields of 1 plane by id", tags = {"PlaneDto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Here is your updated plane."),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
            @ApiResponse(responseCode = "404", description = "Sorry, there are no planes with such id in database")
    })
    @ResponseStatus(HttpStatus.OK)
    PlaneDto updateById(PlaneDto planeForUpdate, Integer id);

    @Operation(summary = "This is endpoint to delete 1 existing plane by its id.", description = "Delete request to remove 1 plane from database by its id", tags = {"PlaneDeleteDto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Plane with this id was deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
            @ApiResponse(responseCode = "404", description = "Sorry, there are no planes with such id in database")
    })
    @ResponseStatus(HttpStatus.OK)
    PlaneDeleteDto removeById(Integer id);

    @Operation(summary = "This is endpoint to find 1 existing plane by its name.", description = "Read request to get 1 plane from database by its name", tags = {"PlaneDto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Here is your plane"),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
            @ApiResponse(responseCode = "404", description = "Sorry, there are no planes with such id in database")
    })
    @ResponseStatus(HttpStatus.OK)
    PlaneDto findPlaneByName(String name);

    @Operation(summary = "This is endpoint to find all existing plane which are fighters.", description = "Read request to get all planes from database by its id", tags = {"Plane"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Plane with this id was deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
            @ApiResponse(responseCode = "404", description = "Sorry, there are no planes which are fighters in database")
    })
    @ResponseStatus(HttpStatus.OK)
    Collection<Plane> findPlaneByFighter();

    @Operation(summary = "This is endpoint to update date and time of creation of 1 plane by its id.", description = "Update request to update date of creation of 1 plane from database by its id", tags = {"PlaneDto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Date of your plane was updated"),
            @ApiResponse(responseCode = "401", description = "Looks like you are not authorized"),
            @ApiResponse(responseCode = "404", description = "Sorry, there are no planes which are fighters in database")
    })
    @ResponseStatus(HttpStatus.OK)
    PlaneDto updateDate(String ldc, Integer id);


}
