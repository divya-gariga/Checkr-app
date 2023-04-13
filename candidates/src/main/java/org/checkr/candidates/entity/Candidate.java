package org.checkr.candidates.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="candidate")
public class Candidate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @ManyToOne
//    @JoinColumn(name="user_id")
    @NotNull
    @Column(name="user_id")
    private int userId;

    @NotEmpty
    @Column(name="name")
    private String name;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;


    @Column(name = "dob")
    private Date dob;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "location")
    private String location;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "social_security")
    private String socialSecurity;

    @Column(name = "driver_license")
    private String driverLicense;

    @Column(name = "date")
    private Date date;

}
