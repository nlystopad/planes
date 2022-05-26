package com.lystopad.planes.repository;

import com.lystopad.planes.domain.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Integer> {
    Plane findByName(String name);

    @Query("select p from Plane p where p.isFighter = true")
    List<Plane> findByFighter();

    @Modifying
    @Transactional
    @Query("update Plane p set p.creationDate =:dateTime where p.id =:id")
    void updatePlane(Integer id, LocalDateTime dateTime);


//    @Query("update EventInvitee set inviteeEmail = ?2 where inviteeEmail = ?1")
//    @Modifying
//    @Transactional
//    void updateInviteeEmails(String oldEmail, String newEmail);
//
//    @Query(value = "select e from Event e where e.eventHost = :user and e.happened = false  and e.canceled = false and (:eventType is null or e.type = :eventType)")
//    @EntityGraph(attributePaths = {"invitees", "visitors", "property", "eventHost", "report", "property.photoUrls"})
//    Page<Event> findAllNewForUser(Pageable pageable, @Param("eventType") EventType eventType, @Param("user") User user);


}
