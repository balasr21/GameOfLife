package com.api.gameoflife.model;

import java.util.Set;

import lombok.Data;

@Data
public class GameInput {

    private int row;
    private int column;
    private Set<Integer> position;

}
