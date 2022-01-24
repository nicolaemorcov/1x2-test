package org.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.dto.Bet;
import org.test.dto.GameType;
import org.test.models.BetModel;
import org.test.repositories.BetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BetServiceImpl implements BetService{

    private final BetRepository betRepository;

    @Autowired
    public BetServiceImpl(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    @Override
    public void insert(Bet bet){
        betRepository.save(toBetModel(bet));
    }

    @Override
    public List<Bet> searchByGameAndClientIdAndDate(GameType gameType, int clientId, LocalDate date){
        List<BetModel> betModels = betRepository.findByGameTypeAndClientIdAndDate(gameType, clientId, date);
        return toBetList(betModels);
    }

    private BetModel toBetModel(Bet bet){
        return new BetModel(bet.getId(), bet.getNumbets(), bet.getGameType(),
                bet.getStake(), bet.getReturns(), bet.getClientId(), bet.getDate());
    }

    private Bet toBet(BetModel betModel){
        return new Bet(betModel.getId(), betModel.getNumbets(), betModel.getGameType(),
                betModel.getStake(), betModel.getReturns(), betModel.getClientId(), betModel.getDate());
    }

    private List<Bet> toBetList(List<BetModel> betModels){
       return betModels.stream().map(this::toBet).collect(Collectors.toList());
    }

}
