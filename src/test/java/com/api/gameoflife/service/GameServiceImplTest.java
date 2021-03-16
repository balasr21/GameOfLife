package com.api.gameoflife.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.api.gameoflife.exception.InvalidRequestException;
import com.api.gameoflife.model.GameInput;
import com.google.gson.Gson;


class GameServiceImplTest {

    GameServiceImpl gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameServiceImpl();
    }

    @Test
    void testCalculateMovements() {
        Gson gson = new Gson();
        GameInput input = GameInput.builder().row(5).column(8).position(new HashSet<>(Arrays.asList(10, 19, 25, 26, 27))).build();
        List<Set<Integer>> result = gameService.calculateMovements(input);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(5, result.size());
        Assertions.assertEquals("[[17,34,19,26,27],[34,35,19,25,27],[34,18,35,27,28],[34,35,19,36,28],[35,36,26,28]]", gson.toJson(result));
    }

    @Test
    void testInvalidInput_Row() {
        GameInput input = GameInput.builder().row(0).column(8).position(new HashSet<>(Arrays.asList(10, 19, 25, 26, 27))).build();
        Assertions.assertEquals("Invalid user request.{\"row\":0,\"column\":8,\"position\":[19,25,10,26,27]}",
                                Assertions.assertThrows(InvalidRequestException.class, () -> gameService.calculateMovements(input)).getMessage());
    }

    @Test
    void testInvalidInput_Column() {
        GameInput input = GameInput.builder().row(5).column(0).position(new HashSet<>(Arrays.asList(10, 19, 25, 26, 27))).build();
        Assertions.assertEquals("Invalid user request.{\"row\":5,\"column\":0,\"position\":[19,25,10,26,27]}",
                                Assertions.assertThrows(InvalidRequestException.class, () -> gameService.calculateMovements(input)).getMessage());
    }

    @Test
    void testInvalidInput_InvalidPosition() {
        GameInput input = GameInput.builder().row(5).column(0).position(null).build();
        Assertions.assertEquals("Invalid user request.{\"row\":5,\"column\":0}",
                                Assertions.assertThrows(InvalidRequestException.class, () -> gameService.calculateMovements(input)).getMessage());
    }

    @Test
    void testInvalidInput_EmptyPosition() {
        GameInput input = GameInput.builder().row(5).column(0).position(new HashSet<>()).build();
        Assertions.assertEquals("Invalid user request.{\"row\":5,\"column\":0,\"position\":[]}",
                                Assertions.assertThrows(InvalidRequestException.class, () -> gameService.calculateMovements(input)).getMessage());
    }

    @Test
    void testInvalidInput() {
        GameInput input = null;
        Assertions.assertEquals("Invalid user request.null",
                                Assertions.assertThrows(InvalidRequestException.class, () -> gameService.calculateMovements(input)).getMessage());
    }


}