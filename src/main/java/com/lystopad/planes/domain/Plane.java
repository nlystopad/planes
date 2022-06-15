package com.lystopad.planes.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "planes")
@Builder
@AllArgsConstructor
@Getter
@Setter
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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Schema(hidden = true)
    @JoinColumn(name = "pilot_id", referencedColumnName = "id")
    private Pilot mainPilot;

    @JsonIgnore
    @Schema(hidden = true)
    private Boolean isDeleted = Boolean.FALSE;

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
        return getAmmunition() == plane.getAmmunition() && isFighter() == plane.isFighter() && getCrewQuantity() == plane.getCrewQuantity() && getId().equals(plane.getId()) && getName().equals(plane.getName()) && getCreationDate().equals(plane.getCreationDate()) && getMainPilot().equals(plane.getMainPilot()) && getIsDeleted().equals(plane.getIsDeleted());
    }

    public boolean compareWithObject(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        Plane plane = (Plane) o;
        return getAmmunition() == plane.getAmmunition() && isFighter() == plane.isFighter() && getCrewQuantity() == plane.getCrewQuantity() && getName().equals(plane.getName()) && getCreationDate().equals(plane.getCreationDate()) && getMainPilot().equals(plane.getMainPilot());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAmmunition(), isFighter(), getCreationDate(), getCrewQuantity(), getMainPilot());
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
