package com.nutn.utm.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Entity(name = "uav")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Uav {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private double weight;

    private String macAddress;

    private int axesNumber;

    private String bodyColor;

    private String flightControl;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private Pilot pilot;
}
