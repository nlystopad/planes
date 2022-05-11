package com.lystopad.planes.repository;

import com.lystopad.planes.domain.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Integer> {
}
