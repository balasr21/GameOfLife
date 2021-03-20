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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/start")
public class GameController {

    @Autowired
    GameService gameService;

    @CrossOrigin
    @PostMapping("/")
    @ApiOperation(value = "Create next generation of live cells", notes = "This API creates next set of generation for Game of Life")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success|Created full set of live cells for each generation"),
            @ApiResponse(code = 400, message = "Invalid format of user input")})
    public ResponseEntity<Set<Integer>> gameOfLife(@RequestBody GameInput input) {
        log.info("Game request received for Row[{}] Column[{}] initialPosition {}", input.getRow(), input.getColumn(), input.getPosition());
        return new ResponseEntity<>(gameService.calculateMovements(input), HttpStatus.OK);
    }

}
