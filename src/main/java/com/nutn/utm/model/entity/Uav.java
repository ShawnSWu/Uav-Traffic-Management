package com.nutn.utm.model.entity;

import javax.persistence.*;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Entity(name = "uav")
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

    public Uav(long id, double weight, String macAddress, Pilot pilot, int axesNumber, String bodyColor, String flightControl) {
        this.id = id;
        this.weight = weight;
        this.macAddress = macAddress;
        this.pilot = pilot;
        this.axesNumber = axesNumber;
        this.bodyColor = bodyColor;
        this.flightControl = flightControl;
    }

    public Uav() {
    }

    public long getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public String getMacAddress() {
        return macAddress;
    }


    public int getAxesNumber() {
        return axesNumber;
    }

    public String getBodyColor() {
        return bodyColor;
    }

    public String getFlightControl() {
        return flightControl;
    }

    public Pilot getPilot() {
        return pilot;
    }
}
