package com.lystopad.planes.service;

import com.lystopad.planes.domain.Pilot;
import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.repository.PilotRepository;
import com.lystopad.planes.repository.PlaneRepository;
import com.lystopad.planes.utils.exception.AlreadyExistException;
import com.lystopad.planes.utils.exception.ResourceNotFoundException;
import com.lystopad.planes.utils.exception.ResourceWasDeletedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@Slf4j
@Service
public class PlaneServiceBean implements PlaneService {

    private final PlaneRepository planeRepository;

    private final PilotRepository pilotRepository;

    @Override
    public Plane create(Plane plane) {
        log.info("create() - start: plane = {}", plane);
        checkAlreadyExist(plane);
        var saved = planeRepository.save(plane);
        log.info("create() - end: id = {}", saved.getId());
        return saved;

    }

    private void checkAlreadyExist(Plane plane) {
        log.info("checkAlreadyExist() -> start: plane = {}", plane);
        Plane byName = planeRepository.findByName(plane.getName());
        if (byName != null && byName.compareWithObject(plane)) {
            throw new AlreadyExistException();
        }
        log.info("checkAlreadyExist() -> end");
    }


    @Override
    public List<Plane> getAll() {
        var all = planeRepository.findAll();
        List<Plane> returnList = new ArrayList<>();
        for (Plane p : all) {
            log.info("getAll() - checking plane with id = {}", p.getId());
            if (p.getDeleted() != null && !p.getDeleted()) {
                returnList.add(p);
            }
        }
        return returnList;
    }

    @Override
    public Plane getById(Integer id) {
        log.info("getById() - start: id = {}", id);
        var plane = returnPlane(id);
        log.debug("getById() -> checkDeleted() - start: id = {}", id);
        checkDeleted(plane);
        log.info("getById() - end: plane = {}", plane);
        return returnPlane(id);
    }

    private void checkDeleted(Plane plane) {
        log.info("checkDeleted() - start: id = {}", plane.getId());
        if (plane.getDeleted() == null || plane.getDeleted()) {
            throw new ResourceWasDeletedException();
        }
        log.info("checkDeleted() - end: id = {}", plane.getId());
    }


    @Override
    public Plane updateById(Plane plane, Integer id) {
        return planeRepository.findById(id)
                .map(entity -> {
                    entity.setAmmunition(plane.getAmmunition());
                    entity.setCreationDate(plane.getCreationDate());
                    entity.setCrewQuantity(plane.getCrewQuantity());
                    entity.setFighter(plane.isFighter());
                    entity.setName(plane.getName());
                    return planeRepository.save(entity);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void removeById(Integer id) {
        log.info("removeById() - start: id = {}", id);
        var plane = returnPlane(id);
        log.info("removeById() -> checkDeleted() - start: id = {}", id);
        checkDeleted(plane);
        log.info("removeById() -> checkDeleted() - end: id = {}", id);
        plane.setDeleted(Boolean.TRUE);
        planeRepository.save(plane);
        log.info("removeById() - end: id = {}", id);
    }


    @Override
    public Plane findPlaneByName(String name) {
        log.info("findPlaneByName() - start: name = {}", name);
        var plane = planeRepository.findByName(name);
        log.info("findPlaneByName() -> checkDeleted() - start: id = {}", plane.getId());
        checkDeleted(plane);
        log.info("findPlaneByName() - end: plane = {}", plane);
        return plane;
    }

    @Override
    public Collection<Plane> findPlaneByFighter() {
        log.info("findPlaneByFighter() - start");
        var collection = planeRepository.findByFighter();
        Collection<Plane> returnList = new ArrayList<>();
        for (Plane p : collection) {
            log.info("findPlaneByFighter() - check deleted for plane with id = {}", p.getId());
            if (p.getDeleted() != null && !p.getDeleted()) {
                returnList.add(p);
            }
        }
        log.info("findPlaneByFighter() - end: collection = {}", collection);
        return returnList;
    }

    @Override
    public void updateDate(Integer id, LocalDateTime dateTime) {
        log.info("updateDate() - start");
        planeRepository.updatePlane(id, dateTime);
        log.info("updateDate() - end");
    }

    @Override
    public Pilot getPilotByPlaneId(Integer id) {
        log.info("getPilotByPlaneId() - start : id = {}", id);
        var pilot = pilotRepository.getPilotByIdOfPlane(id);
        log.info("getPilotByPlaneId() - end : pilot = {}", pilot);
        return pilot;
    }

    @Override
    public Plane addMainPilot(Integer id, Pilot pilot) {
        return planeRepository.findById(id)
                .map(entity -> {
                    entity.setMainPilot(pilot);
                    return planeRepository.save(entity);
                }).orElseThrow(ResourceNotFoundException::new);
    }


    /**
     * technical method that return Plane by id or throw EntityNotFoundException
     *
     * @param id - id of plane that should be returned
     * @return Plane
     */
    private Plane returnPlane(Integer id) {
        return planeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
