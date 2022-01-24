package org.test.services;

import org.test.dto.Bet;
import org.test.dto.GameType;

import java.time.LocalDate;
import java.util.List;

public interface BetService {
    void insert(Bet bet);

    List<Bet> searchByGameAndClientIdAndDate(GameType gameType, int clientId, LocalDate date);
}
