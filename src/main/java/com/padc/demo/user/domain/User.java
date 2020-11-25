package com.padc.demo.user.domain;

import com.padc.demo.core.auditing.Audition;
import lombok.*;

import com.padc.demo.deck.domain.Deck;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


//https://github.com/springframeworkguru/ssc-brewery/blob/db-project-lombok/src/main/java/guru/sfg/brewery/domain/security/User.java
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="user")
@SecondaryTable(name="contact_info")
//@SecondaryTable(name="login_info")
public class User extends Audition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ved ikke, om de virker i deres egen tabel
    private String username;
    private String password;

    //kun en rolle i dette system
    private Role role;

    @NotBlank(message = "Indtast venligst fornavn")
    private String firstName;

    @NotBlank(message = "Indtast venligst efternavn")
    private String lastName;

    @Column(table ="contact_info")
    private String emailAddress;

    @Column(table ="contact_info")
    private String phoneNumber;

    /*
    //jeg kan senere prøve at have disse i sin egen entity
    @Column(table = "login_info")
    private String userName;

    @Column(table = "login_info")
    private String password;*/


//    //mappedby is the tablename
//    @OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            mappedBy = "company")
//    private List<Deck> listOfDecks;




}
