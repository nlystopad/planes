package com.lystopad.planes.repository;

import com.lystopad.planes.domain.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PilotRepository extends JpaRepository<Pilot, Integer> {

    @Query("SELECT p from Pilot p inner join Plane pl on p.id = pl.mainPilot.id")
    Pilot getPilotByIdOfPlane(Integer id);
}
