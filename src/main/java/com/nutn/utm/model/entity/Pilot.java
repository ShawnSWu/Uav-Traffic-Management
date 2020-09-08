package com.nutn.utm.model.entity;

import javax.persistence.*;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Entity(name = "pilot")
public class Pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String account;

    private String hashedPassword;

    private String name;

    private String phoneNumber;

    private String email;

    private String institution;

    public Pilot(long id, String account, String hashedPassword, String name, String phoneNumber, String email, String institution) {
        this.id = id;
        this.account = account;
        this.hashedPassword = hashedPassword;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.institution = institution;
    }

    public Pilot() {
    }

    public long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getInstitution() {
        return institution;
    }
}
