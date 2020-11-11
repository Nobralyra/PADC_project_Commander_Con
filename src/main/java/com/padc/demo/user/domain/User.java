package com.padc.demo.user.domain;

import com.padc.demo.core.auditing.Audition;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="user")
@SecondaryTable(name="contact_info")
@SecondaryTable(name="login_info")
public class User extends Audition {

    //Databasen: når jeg vil slette en user, skla jeg først slette contact_info og login_info
    //fordi user er parent til dem.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Indtast venligst fornavn")
    private String firstName;
    @NotBlank(message = "Indtast venligst efternavn")
    private String lastName;

    @Column(table ="contact_info")
    private String emailAddress;

    @Column(table ="contact_info")
    private String phoneNumber;

    @Column(table = "login_info")
    private String userName;

    @Column(table = "login_info")
    private String password;

    public User(){}

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
