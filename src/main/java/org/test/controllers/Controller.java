package org.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.test.components.JsonFileParserService;
import org.test.dto.GameType;
import org.test.services.BetService;

import java.time.LocalDate;

@RestController
public class Controller {

    private final JsonFileParserService jsonFileParserService;

    private final BetService betService;

    @Autowired
    public Controller(JsonFileParserService jsonFileParserService, BetService betService) {
        this.jsonFileParserService = jsonFileParserService;
        this.betService = betService;
    }


    @GetMapping(value = "/search")
    public ResponseEntity<String> searchByGameAndClientIdAndDate(@RequestParam String game, @RequestParam String clientId, @RequestParam String date){
        GameType gameType = GameType.getGameType(game);
        LocalDate localDate = LocalDate.parse(date);
        betService.searchByGameAndClientIdAndDate(gameType, Integer.parseInt(clientId), localDate);
        return ResponseEntity.ok("Process Finished successfully");
    }

    @GetMapping(value = "/process-file-and-insert", params = {"fileLocation"})
    public ResponseEntity<String> readAndInsertFile(String fileLocation){
        jsonFileParserService.parseAndInsertFile(fileLocation);
        return ResponseEntity.ok("Process Finished successfully");
    }



}
