package com.lystopad.planes.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "planes")
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private int ammunition;
    private boolean isFighter;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime creationDate;
    private int crewQuantity;
    @JsonIgnore
    private Boolean isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(int ammunition) {
        this.ammunition = ammunition;
    }

    public boolean isFighter() {
        return isFighter;
    }

    public void setFighter(boolean fighter) {
        isFighter = fighter;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getCrewQuantity() {
        return crewQuantity;
    }

    public void setCrewQuantity(int crewQuantity) {
        this.crewQuantity = crewQuantity;
    }

    @JsonIgnore
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        Plane plane = (Plane) o;
        return getAmmunition() == plane.getAmmunition() && isFighter() == plane.isFighter() && getCrewQuantity() == plane.getCrewQuantity() && getId().equals(plane.getId()) && getName().equals(plane.getName()) && getCreationDate().equals(plane.getCreationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAmmunition(), isFighter(), getCreationDate(), getCrewQuantity());
    }

    @Override
    public String toString() {
        return "Plane {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", ammunition = " + ammunition +
                ", isFighter = " + isFighter +
                ", creationDate = " + creationDate +
                ", crewQuantity = " + crewQuantity + "}";
    }
}
