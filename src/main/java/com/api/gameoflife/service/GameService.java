package com.api.gameoflife.service;

import java.util.Set;

import com.api.gameoflife.model.GameInput;

public interface GameService {

    Set<Integer> calculateMovements(GameInput input);

}
