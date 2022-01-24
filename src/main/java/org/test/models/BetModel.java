package org.test.models;

import org.test.dto.GameType;

import javax.persistence.*;
import java.time.LocalDate;


//The field id is automatically indexed
//More indexes can be added, depending on the requirements and use-cases.
@Entity
@Table(indexes = {
        @Index(name = "date", columnList = "date"),
        @Index(name = "date", columnList = "clientId"),
        @Index(name = "date", columnList = "gameType"),
        @Index(name = "date", columnList = "clientId, gameType"),
        @Index(name = "date", columnList = "date, gameType"),
        @Index(name = "date", columnList = "date, clientId"),
        @Index(name = "date", columnList = "date, clientId, gameType"),
        @Index(name = "date", columnList = "id, date, clientId, gameType"),
})
public class BetModel {

    @Id
    private int id;
    @Column(name = "numbets", nullable = false)
    private int numbets;
    @Column(name = "gameType", nullable = false)
    @Enumerated(EnumType.STRING)
    private GameType gameType;
    @Column(name = "stake", nullable = false)
    private double stake;
    @Column(name = "returns", nullable = false)
    private double returns;
    @Column(name = "clientId", nullable = false)
    private int clientId;
    @Column(name = "date", nullable = false)
    private LocalDate date;

    public BetModel() {
    }

    public BetModel(int id, int numbets, GameType gameType, double stake, double returns, int clientId, LocalDate date) {
        this.id = id;
        this.numbets = numbets;
        this.gameType = gameType;
        this.stake = stake;
        this.returns = returns;
        this.clientId = clientId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getNumbets() {
        return numbets;
    }

    public GameType getGameType() {
        return gameType;
    }

    public double getStake() {
        return stake;
    }

    public double getReturns() {
        return returns;
    }

    public int getClientId() {
        return clientId;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", numbets=" + numbets +
                ", gameType=" + gameType +
                ", stake=" + stake +
                ", returns=" + returns +
                ", clientId=" + clientId +
                ", date=" + date +
                '}';
    }
}
