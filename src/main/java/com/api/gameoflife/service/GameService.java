package com.api.gameoflife.service;

import java.util.List;
import java.util.Set;

import com.api.gameoflife.model.GameInput;

public interface GameService {

    List<Set<Integer>> calculateMovements(GameInput input);

}
