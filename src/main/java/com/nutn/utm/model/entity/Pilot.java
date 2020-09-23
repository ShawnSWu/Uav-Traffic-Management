package com.nutn.utm.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Entity(name = "pilot")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
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

}
