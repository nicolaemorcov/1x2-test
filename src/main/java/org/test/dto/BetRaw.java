package org.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BetRaw implements Serializable {

    private final int id;
    private final int numbets;
    private final String game;
    private final double stake;
    private final double returns;
    private final int clientId;
    private final String date;

    public BetRaw(@JsonProperty("id") int id, @JsonProperty("numbets") int numbets, @JsonProperty("game") String game,
                  @JsonProperty("stake") double stake, @JsonProperty("returns") double returns,
                  @JsonProperty("clientid") int clientId, @JsonProperty("date") String date) {
        this.id = id;
        this.numbets = numbets;
        this.game = game;
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

    public String getGame() {
        return game;
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

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "BetRaw{" +
                "id=" + id +
                ", numbets=" + numbets +
                ", game='" + game + '\'' +
                ", stake=" + stake +
                ", returns=" + returns +
                ", clientId=" + clientId +
                ", date='" + date + '\'' +
                '}';
    }
}

