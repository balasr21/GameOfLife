package com.api.gameoflife.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.gameoflife.exception.InvalidRequestException;
import com.api.gameoflife.model.GameInput;
import com.api.gameoflife.service.GameService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/start")
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping("/")
    public ResponseEntity<List<Set<Integer>>> gameOfLife(@RequestBody GameInput input) {
        log.info("Game request received for Row[{}] Column[{}] initialPosition [{}]", input.getRow(), input.getColumn(), input.getPosition());
        try {
            gameService.calculateMovements(input);
            return new ResponseEntity<>(gameService.calculateMovements(input), HttpStatus.OK);
        } catch (InvalidRequestException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
