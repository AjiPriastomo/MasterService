package com.testcode.masterservice.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "unit")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @OneToMany(mappedBy = "unit")
    @JsonIgnoreProperties(value = "unit", allowSetters = true)
    private Set<Camera> cameras;

    @Column(name = "is_assigned")
    private Boolean assigned;

    @Enumerated(EnumType.STRING)
    @JsonAlias("unit_type")
    @Column(name = "unit_type")
    private UnitType unitType;

    public enum UnitType{
        VEHICLE,OFFICER,DESK
    }
}
