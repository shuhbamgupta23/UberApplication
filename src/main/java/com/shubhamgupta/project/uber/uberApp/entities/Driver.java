package com.shubhamgupta.project.uber.uberApp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;


@Data
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;

    private Boolean available;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    Point currentLocation;
}




