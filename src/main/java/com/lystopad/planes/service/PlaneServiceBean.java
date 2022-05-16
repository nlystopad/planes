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


@Service
@AllArgsConstructor
@Slf4j
public class PlaneServiceBean implements PlaneService {

    private final PlaneRepository planeRepository;

    @Override
    public Plane create(Plane plane) {
        checkDateOfCreation(plane);
        return planeRepository.save(plane);
    }


    @Override
    public List<Plane> getAll() {
        return planeRepository.findAll();
    }

    @Override
    public Plane getById(Integer id) {
        return planeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Plane with this ID doesn't exist"));
    }

    @Override
    public Plane updateById(Plane plane, Integer id) {
        checkDateOfCreation(plane);
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
        planeRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        planeRepository.deleteAll();
    }

    @Override
    public Collection<Plane> findPlaneByName(String name) {
        log.info("findPlaneByName() - start: name = {}", name);
        Collection<Plane> collection = planeRepository.findByName(name);
        log.info("findPlaneByName() - end: collection = {}", collection);
        return collection;
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
     * technical method that checks current plane's date of creation
     *
     * @param plane - plane to check
     */
    private void checkDateOfCreation(Plane plane) {
        if (plane.getCreationDate().isBefore(LocalDateTime.of(1970, 1, 1, 0, 0))) {
            // todo: create my own exception to throw
            throw new RuntimeException("Plane is too old, you should utilize it");
        }
    }


}
