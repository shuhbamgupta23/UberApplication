package com.shubhamgupta.project.uber.uberApp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;
}
