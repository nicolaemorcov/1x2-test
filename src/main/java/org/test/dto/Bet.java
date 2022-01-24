package org.test.dto;

import java.time.LocalDate;

public class Bet {

    private final int id;
    private final int numbets;
    private final GameType gameType;
    private final double stake;
    private final double returns;
    private final int clientId;
    private final LocalDate date;


    public Bet(int id, int numbets, GameType gameType, double stake, double returns, int clientId, LocalDate date) {
        this.id = id;
        this.numbets = numbets;
        this.gameType = gameType;
        this.stake = stake;
        this.returns = returns;
        this.clientId = clientId;
        this.date = date;
    }

    public Bet(BetRaw betRaw) {
        this.id = betRaw.getId();
        this.numbets = betRaw.getNumbets();
        this.gameType = GameType.getGameType(betRaw.getGame());
        this.stake = betRaw.getStake();
        this.returns = betRaw.getReturns();
        this.clientId = betRaw.getClientId();
        this.date = createLocalDate(betRaw.getDate());

    }

    public int getId() {
        return id;
    }

    private LocalDate createLocalDate(String date){
        return LocalDate.parse(date);
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
