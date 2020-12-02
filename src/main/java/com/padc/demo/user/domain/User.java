package com.padc.demo.user.domain;

import com.padc.demo.core.auditing.Audition;
import com.padc.demo.core.enums.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


//https://github.com/springframeworkguru/ssc-brewery/blob/db-project-lombok/src/main/java/guru/sfg/brewery/domain/security/User.java
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="user")
@SecondaryTable(name="contact_info")
@SecondaryTable(name="login_info")
public class User extends Audition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Indtast venligst brugernavn")
    @Column(table = "login_info")
    private String username;
    @NotBlank(message = "Indtast venligst kodeord")
    @Column(table = "login_info")
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


//    //mappedby is the tablename
//    @OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            mappedBy = "company")
//    private List<Deck> listOfDecks;




}
