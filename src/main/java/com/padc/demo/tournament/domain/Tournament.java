package com.padc.demo.tournament.domain;

import com.padc.demo.core.Audition;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "tournament")
@SecondaryTable(name = "tournament_type_info")
public class Tournament extends Audition
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tournamentId;

    @NotBlank(message = "Skal udfyldes")
    private String tournamentName;

    /**
     * https://stackoverflow.com/questions/45629318/spring-with-thymeleaf-binding-date-in-html-form
     * Should use LocalDateTime so the HTML datatime_local can get saved in the field. The also gets annotateret with
     * @DateTimeFormat so the LocalDateTime accepts the ISO 8601
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Skal udfyldes")
    private LocalDateTime dateAndTime;

    private String place;

    @Column(table = "tournament_type_info")
    private String tournamentType;

    @Column(table = "tournament_type_info")
    private String pointsType;

    @Lob
    @Column(table = "tournament_type_info")
    private String furtherInformation;

    public Tournament()
    {

    }

    public Tournament(long tournamentId, @NotBlank(message = "Indtast venligst turnerningsnavnet") String tournamentName, @NotNull LocalDateTime dateAndTime, String place, String tournamentType, String pointsType, String furtherInformation)
    {
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.dateAndTime = dateAndTime;
        this.place = place;
        this.tournamentType = tournamentType;
        this.pointsType = pointsType;
        this.furtherInformation = furtherInformation;
    }

    public long getTournamentId()
    {
        return tournamentId;
    }

    public void setTournamentId(long tournamentId)
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
