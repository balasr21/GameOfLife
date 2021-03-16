package com.api.gameoflife.model;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GameInput {

    private int row;
    private int column;
    private Set<Integer> position;

}
