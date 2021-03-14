package com.api.gameoflife.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.api.gameoflife.model.GameInput;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    @Override public List<Set<Integer>> calculateMovements(GameInput input) {

        List<Set<Integer>> movements = new ArrayList<>();

        int initialSize = input.getPosition().size();
        while (input.getPosition().size() >= initialSize) {

            Set<Integer> initialPos = input.getPosition();
            Set<Integer> tempSet = new HashSet<>();
            input.getPosition().stream().forEach(g -> tempSet.addAll(findNeighbours(g, input.getRow(), input.getColumn())));

            input.setPosition(new HashSet<>());
            tempSet.stream().forEach(g -> {
                if (isAlive(g, initialPos, findNeighbours(g, input.getRow(), input.getColumn())) == 1) {
                    input.getPosition().add(g);
                }
            });
            movements.add(input.getPosition());

        }

        return movements;
    }

    private int isAlive(int currentCell, Set<Integer> initialPos, Set<Integer> neighbourCells) {

        AtomicInteger liveCellsCount = new AtomicInteger(0);
        neighbourCells.stream().forEach(g -> {
            if (initialPos.contains(g)) {
                liveCellsCount.getAndIncrement();
            }
        });

        if (initialPos.contains(currentCell)) {
            if (liveCellsCount.get() < 2) {
                return 0;
            } else if (liveCellsCount.get() > 1 && liveCellsCount.get() < 4) {
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

    private Set<Integer> findNeighbours(Integer g, int row, int column) {
        Set<Integer> neighbours = new HashSet<>();

        boolean isLeftPossible = false;
        boolean isRightPossible = false;
        boolean isBottomPossible = false;
        boolean isTopPossible = false;

        // Left
        if (g - 1 > 0 && ((g - 1) % column) != 0) {
            isLeftPossible = true;
            neighbours.add(g - 1);
        }
        //Right
        if (g + 1 % column != 1) {
            isRightPossible = true;
            neighbours.add(g + 1);
        }
        // Top
        if (g - column > 0) {
            isTopPossible = true;
            neighbours.add(g - column);
        }
        //Bottom
        if (g + column < (row * column)) {
            isBottomPossible = true;
            neighbours.add(g + column);
        }

        if (isLeftPossible && isBottomPossible) {
            //BottomLeft
            neighbours.add(g + (column - 1));
        }

        if (isRightPossible && isTopPossible) {
            //TopRight
            neighbours.add(g - (column - 1));
        }

        if (isTopPossible && isLeftPossible) {
            //TopLeft
            neighbours.add(g - (column + 1));
        }
        if (isBottomPossible && isRightPossible) {
            //BottomRight
            neighbours.add(g + (column + 1));
        }

        return neighbours;
    }

}
