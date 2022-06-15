package com.lystopad.planes.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "pilots")
public class Pilot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Schema(description = "unique id of pilot", pattern = "sequence")
    private Integer id;

    @Schema(description = "pilots first name", example = "Jonathan")
    private String firstName;

    @Schema(description = "pilots last name", example = "Smith")
    private String lastName;

    @Schema(description = "pilots callsign", example = "Charlie")
    private String callsign;

    @Schema(description = "age of pilot", maxLength = 2)
    private Integer age;

    @Schema(hidden = true)
    @OneToOne(mappedBy = "mainPilot")
    private Plane plane;

}
