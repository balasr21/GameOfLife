package com.api.gameoflife.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.api.gameoflife.exception.InvalidRequestException;
import com.api.gameoflife.model.GameInput;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    @Override
    public Set<Integer> calculateMovements(GameInput input) {

        if (input == null || input.getRow() <= 0 || input.getColumn() <= 0 || input.getPosition().isEmpty()) {
            Gson gson = new Gson();
            log.error("Invalid user request.", gson.toJson(input));
            throw new InvalidRequestException("Invalid user request." + gson.toJson(input));
        }

        Set<Integer> movements = new HashSet<>();

        Set<Integer> initialPos = input.getPosition();
        Set<Integer> tempSet = new HashSet<>();
        // Identify the neighbour cell for each of the live cells
        input.getPosition().stream().forEach(g -> tempSet.addAll(findNeighbours(g, input.getRow(), input.getColumn())));

        input.setPosition(new HashSet<>());
        tempSet.stream().forEach(g -> {
            // For each of the neighbours of the live cells identify whether they will be alive in next gen
            if (isAlive(g, initialPos, findNeighbours(g, input.getRow(), input.getColumn())) == 1) {
                input.getPosition().add(g);
            }
        });
        movements.addAll(input.getPosition());
        return movements;
    }

    /**
     * This method identifies whether the given cell will be alive or not in the nextGen based on the GameOfLife Rules
     *
     * @param currentCell
     * @param initialPos
     * @param neighbourCells
     * @return
     */
    private int isAlive(int currentCell, Set<Integer> initialPos, Set<Integer> neighbourCells) {

        AtomicInteger liveCellsCount = new AtomicInteger(0);
        neighbourCells.stream().forEach(g -> {
            if (initialPos.contains(g)) {
                liveCellsCount.getAndIncrement();
            }
        });

        if (initialPos.contains(currentCell)) {
            if (liveCellsCount.get() > 1 && liveCellsCount.get() < 4) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (liveCellsCount.get() == 3) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * This Method identifies the neighbour for the current cell
     *
     * @param currentCell
     * @param row
     * @param column
     * @return
     */
    private Set<Integer> findNeighbours(Integer currentCell, int row, int column) {
        Set<Integer> neighbours = new HashSet<>();

        boolean isLeftPossible = false;
        boolean isRightPossible = false;
        boolean isBottomPossible = false;
        boolean isTopPossible = false;

        // Left
        if (currentCell - 1 > 0 && ((currentCell - 1) % column) != 0) {
            isLeftPossible = true;
            neighbours.add(currentCell - 1);
        }
        //Right
        if (currentCell + 1 % column != 1) {
            isRightPossible = true;
            neighbours.add(currentCell + 1);
        }
        // Top
        if (currentCell - column > 0) {
            isTopPossible = true;
            neighbours.add(currentCell - column);
        }
        //Bottom
        if (currentCell + column < (row * column)) {
            isBottomPossible = true;
            neighbours.add(currentCell + column);
        }

        if (isLeftPossible && isBottomPossible) {
            //BottomLeft
            neighbours.add(currentCell + (column - 1));
        }

        if (isRightPossible && isTopPossible) {
            //TopRight
            neighbours.add(currentCell - (column - 1));
        }

        if (isTopPossible && isLeftPossible) {
            //TopLeft
            neighbours.add(currentCell - (column + 1));
        }
        if (isBottomPossible && isRightPossible) {
            //BottomRight
            neighbours.add(currentCell + (column + 1));
        }

        return neighbours;
    }

}
