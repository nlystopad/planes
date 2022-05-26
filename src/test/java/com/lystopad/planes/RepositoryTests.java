package com.lystopad.planes;

import com.lystopad.planes.domain.Plane;
import com.lystopad.planes.repository.PlaneRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositoryTests {

    @Autowired
    private PlaneRepository planeRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void savePlaneTest() {
        Plane plane = Plane.builder().name("F-18").ammunition(5).isFighter(true).build();
        planeRepository.save(plane);
        Assertions.assertThat(plane.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getPlaneTest() {
        Plane plane = planeRepository.findById(1).orElseThrow();
        Assertions.assertThat(plane.getId()).isEqualTo(1);

    }

    @Test
    @Order(3)
    public void getListOfPlanes() {
        List<Plane> planeList = planeRepository.findAll();
        Assertions.assertThat(planeList.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void findByFighterTest() {
        List<Plane> planeList = planeRepository.findByFighter();
        Assertions.assertThat(planeList.size()).isGreaterThan(0);
    }

    @Test
    @Order(5)
    public void updatePlaneTest() {
        Plane plane = planeRepository.findById(1).get();
        plane.setName("F-16");
        Plane updated = planeRepository.save(plane);

        Assertions.assertThat(updated.getName()).isEqualTo("F-16");
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    public void deletePlaneTest() {
        Plane plane = planeRepository.findById(1).get();
        planeRepository.delete(plane);
        Plane plane1 = null;
        Optional<Plane> optionalPlane = Optional.ofNullable(planeRepository.findByName("F-16"));
        if (optionalPlane.isPresent()) {
            plane1 = optionalPlane.get();
        }
        Assertions.assertThat(plane1).isNull();
    }
}
