package com.blood.donation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NamedQuery(name = "User.findByUserName", query = "select u from User u where u.userName=:userName")
@Entity
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String userType;
    private Boolean isFirstLogin;

}

