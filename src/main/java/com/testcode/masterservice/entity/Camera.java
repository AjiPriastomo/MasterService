package com.testcode.masterservice.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "camera")
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "serial_number", nullable = true)
    @JsonAlias("serial_number")
    private String serialNumber;

    @Column(name = "type", nullable = true)
    private String type;

    @Column(name = "model", nullable = true)
    private String model;

    @Column(name = "brand", nullable = true)
    private String brand;

    @Column(name = "add_date", nullable = true)
    @JsonAlias("add_date")
    private LocalDateTime addDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id")
    private Unit unit;
}
