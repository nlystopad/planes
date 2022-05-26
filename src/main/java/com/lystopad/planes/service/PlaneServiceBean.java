package com.lystopad.planes.service;

import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.repository.PlaneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;



@AllArgsConstructor
@Slf4j
@Service
public class PlaneServiceBean implements PlaneService {

    private final PlaneRepository planeRepository;

    @Override
    public Plane create(Plane plane) {
        return planeRepository.save(plane);
    }


    @Override
    public List<Plane> getAll() {
        return planeRepository.findAll();
    }

    @Override
    public Plane getById(Integer id) {
        log.info("getById() - start: id = {}", id);
        Plane plane = returnPlane(id);
        log.debug("getById() -> checkDeleted() - start: id = {}", id);
        checkDeleted(plane);
        log.info("getById() - end: plane = {}", plane);
        return returnPlane(id);
    }

    private void checkDeleted(Plane plane) {
        log.info("checkDeleted() - start: id = {}", plane.getId());
        if (plane.getDeleted() == null || plane.getDeleted()) {
            throw new EntityNotFoundException("Plane was deleted");
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
                .orElseThrow(() -> new EntityNotFoundException("Plane with this ID doesn't exist"));
    }

    @Override
    public void removeById(Integer id) {
        returnPlane(id).setDeleted(Boolean.TRUE);
        planeRepository.save(returnPlane(id));
    }

    @Override
    public void removeAll() {
        planeRepository.deleteAll();
    }

    @Override
    public Plane findPlaneByName(String name) {
        log.info("findPlaneByName() - start: name = {}", name);
        Plane plane = planeRepository.findByName(name);
        log.info("findPlaneByName() - end: collection = {}", plane);
        return plane;
    }

    @Override
    public Collection<Plane> findPlaneByFighter() {
        log.info("findPlaneByFighter() - start");
        Collection<Plane> collection = planeRepository.findByFighter();
        log.info("findPlaneByFighter() - end: collection = {}", collection);
        return collection;
    }

    @Override
    public void updateDate(Integer id, LocalDateTime dateTime) {
        log.info("updateDate() - start");
        planeRepository.updatePlane(id, dateTime);
        log.info("updateDate() - end");
    }



    /**
     * technical method that return Plane by id or throw EntityNotFoundException
     *
     * @param id - id of plane that should be returned
     * @return Plane
     */
    private Plane returnPlane(Integer id) {
        return planeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Plane with such id doesn't exist"));
    }
}
