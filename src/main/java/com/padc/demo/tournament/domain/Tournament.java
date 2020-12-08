package com.padc.demo.tournament.domain;

import com.padc.demo.core.auditing.Audition;
import com.padc.demo.core.enums.GameType;
import com.padc.demo.core.enums.PointType;
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
    private Long tournamentId;

    @NotBlank(message = "Skal udfyldes")
    private String tournamentName;

    /**
     * Should use LocalDateTime so the HTML datatime_local can get saved in the field. The also gets annotateret with
     * "@DateTimeFormat" so Spring/Thymeleaf knows hot to format the values correctly
     * The LocalDateTime accepts the ISO 8601
     *
     * https://stackoverflow.com/questions/45629318/spring-with-thymeleaf-binding-date-in-html-form
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Skal udfyldes")
    private LocalDateTime dateAndTime;

    private String place;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('EDH','CEDH', 'ANDET')")
    @NotNull(message = "Skal udfyldes")
    private GameType gameType;

    @Enumerated(EnumType.STRING)
    @Column(table = "tournament_type_info", columnDefinition = "enum('ELIMINERING','POINT', 'MILESTONE', 'ANDET')")
    @NotNull(message = "Skal udfyldes")
    private PointType pointType;

    @Lob
    @Column(table = "tournament_type_info")
    private String furtherInformation;

    public Tournament()
    {

    }

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

    public GameType getGameType()
    {
        return gameType;
    }

    public void setGameType(GameType gameType)
    {
        this.gameType = gameType;
    }

    public PointType getPointType()
    {
        return pointType;
    }

    public void setPointType(PointType pointType)
    {
        this.pointType = pointType;
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
