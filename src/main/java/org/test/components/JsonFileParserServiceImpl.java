package org.test.components;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.test.dto.Bet;
import org.test.dto.BetRaw;
import org.test.kafka.config.Producer;
import org.test.services.BetService;

import java.io.File;
import java.io.IOException;

@Component
public class JsonFileParserServiceImpl implements JsonFileParserService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final BetService betService;

    private final Producer producer;

    private static final String TOPIC = "bet_detail";

    private static final String BETS_NODE = "bets";

    @Autowired
    public JsonFileParserServiceImpl(BetService betService, Producer producer) {
        this.betService = betService;
        this.producer = producer;
    }

    //As per requirement, every bet is inserted individually.
    // The option I would do is adding all the bets to a list/set, and insert all at once,
    // as this would be expensive to hit the database for every record we want to insert
    @Override
    public void parseAndInsertFile(String path){
        try {
            File file = new File(path);
            JsonNode root = objectMapper.readTree(file);
            JsonNode bets = root.path(BETS_NODE);
            for (JsonNode rawBet : bets) {
                String rawBetAsString = rawBet.toString();
                BetRaw betRaw = objectMapper.readValue(rawBetAsString, BetRaw.class);
                Bet bet = new Bet(betRaw);
                betService.insert(bet);
                publishInsertedMessages(betRaw);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void publishInsertedMessages(BetRaw bet){
        producer.send(TOPIC, bet);
    }

}
