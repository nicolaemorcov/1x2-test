package org.test.dto;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum GameType {

    ROULETTE("roulette"),
    BACCARAT("baccarat"),
    BLACKJACK("blackjack");

    private static final Map<String, GameType> GAME_TYPE_MAP = new HashMap<>();

    static {
        for (GameType gameType : values()) {
            GAME_TYPE_MAP.put(gameType.value, gameType);
        }
    }


    private final String value;

    GameType(String value) {
        this.value = value;
    }

    public static GameType getGameType(String game){
        return GAME_TYPE_MAP.get(game);
    }

}
