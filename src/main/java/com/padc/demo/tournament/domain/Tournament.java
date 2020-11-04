package com.padc.demo.tournament.domain;

import com.padc.demo.core.Audition;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "tournament")
@SecondaryTable(name = "tournament_type_info")
public class Tournament extends Audition
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tournamentId;

    @NotBlank(message = "Indtast venligst turnerningsnavnet")
    private String tournamentName;

    @CreationTimestamp
    //@Column(updatable = false)
    private LocalDateTime dateAndTime;

    private String place;

    @Column(table = "tournament_type_info")
    private String tournamentType;

    @Column(table = "tournament_type_info")
    private String pointsType;

    @Lob
    @Column(table = "tournament_type_info")
    private String furtherInformation;

    public Long getTournamentId()
    {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId)
    {
        this.tournamentId = tournamentId;
    }

    public String getTournamentName()
    {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName)
    {
        this.tournamentName = tournamentName;
    }

    public LocalDateTime getDateAndTime()
    {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime)
    {
        this.dateAndTime = dateAndTime;
    }

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public String getTournamentType()
    {
        return tournamentType;
    }

    public void setTournamentType(String tournamentType)
    {
        this.tournamentType = tournamentType;
    }

    public String getPointsType()
    {
        return pointsType;
    }

    public void setPointsType(String pointsType)
    {
        this.pointsType = pointsType;
    }

    public String getFurtherInformation()
    {
        return furtherInformation;
    }

    public void setFurtherInformation(String furtherInformation)
    {
        this.furtherInformation = furtherInformation;
    }
}
