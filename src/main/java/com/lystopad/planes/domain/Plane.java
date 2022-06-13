package com.lystopad.planes.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "planes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Schema(description = "id of plane", pattern = "sequence")
    private Integer id;
    @Schema(description = "Name of plane", example = "F-16", required = true)
    private String name;
    @Schema(description = "Quantity of planes ammunition", example = "18")
    private int ammunition;
    @Schema(description = "Flag which should be set if plane is fighter", allowableValues = {"true", "false"}, name = "Fighter")
    private boolean isFighter;
    @Schema(description = "Date and time of creation of plane", pattern = "yyyy-MM-dd HH:mm", required = true, name = "Creation date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime creationDate;
    @Schema(description = "Quantity of crew of this plane", name = "Crew quantity", example = "2")
    private int crewQuantity;
    @JsonIgnore
    @Schema(hidden = true)
    private Boolean isDeleted = Boolean.FALSE;

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

    public boolean compareWithObject(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        Plane plane = (Plane) o;
        return getAmmunition() == plane.getAmmunition() && isFighter() == plane.isFighter() && getCrewQuantity() == plane.getCrewQuantity() && getName().equals(plane.getName()) && getCreationDate().equals(plane.getCreationDate());
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
