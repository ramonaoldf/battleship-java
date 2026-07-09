package org.example;

import java.util.List;
import java.util.Map;

class Config {

    public enum BoardType { S, M, L }

    public static final Map<BoardType, Integer> BOARD_SIZES = Map.of(
            BoardType.S, 5,
            BoardType.M, 10,
            BoardType.L, 20
    );

    public static final Map<BoardType, List<Integer>> SHIP_SIZES = Map.of(
            BoardType.S, List.of(1, 2),
            BoardType.M, List.of(1, 1, 2, 3),
            BoardType.L, List.of(1, 1, 1, 2, 2, 3, 3)
    );
}
